package net.tiny.feature.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import net.tiny.dao.BaseDao;
import net.tiny.feature.entity.BaseCategory;

/**
 * BaseCategoryDao - 抽象分类
 *
 */
public abstract class BaseCategoryDao<T extends BaseCategory, ID extends Serializable> extends BaseDao<T, ID> {

    private static final String FIND_ROOTS    = "select a from %s a where a.parent is null order by a.order asc";
    private static final String FIND_PARENTS  = "select a from %s a where a.id in :ids order by a.grade asc";
    private static final String FIND_CHILDREN = "select a from %s a where a.tree like :tree order by a.order asc";
    private static final String FIND_ALL      = "select a from %s a order by a.order asc";

    /**
     * 获取实体对象名
     *
     * @return 实体对象名
     */
    protected String getEntityName() {
        return getEntityType().getSimpleName();
    }

    /**
     * 查找顶级分类
     *
     * @param count
     *            数量
     * @return 顶级分类
     */
    public List<T> findRoots(Integer count) {
        TypedQuery<T> query = getTypedQuery(FIND_ROOTS)
                .setFlushMode(FlushModeType.COMMIT);
        if (count != null) {
            query.setMaxResults(count);
        }
        return query.getResultList();
    }
    /**
     * 查找上级分类
     *
     * @param category
     *            分类
     * @param count
     *            数量
     * @return 上级分类
     */
    public List<T> findParents(BaseCategory category, Integer count) {
        if (category == null || category.getParent() == null) {
            return Collections.<T> emptyList();
        }
        TypedQuery<T> query = getTypedQuery(FIND_PARENTS)
                .setFlushMode(FlushModeType.COMMIT)
                .setParameter("ids", category.getTreePaths());
        if (count != null) {
            query.setMaxResults(count);
        }
        return query.getResultList();
    }
    /**
     * 查找下级分类
     *
     * @param category
     *            分类
     * @param count
     *            数量
     * @return 下级分类
     */
    public List<T> findChildren(BaseCategory category, Integer count) {
        TypedQuery<T> query;
        if (category != null) {
            query = getTypedQuery(FIND_CHILDREN)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("tree", "%" + BaseCategory.TREE_PATH_SEPARATOR + category.getId() + BaseCategory.TREE_PATH_SEPARATOR + "%");
        } else {
            query = getTypedQuery(FIND_ALL)
                    .setFlushMode(FlushModeType.COMMIT);
        }
        if (count != null) {
            query.setMaxResults(count);
        }
        return sort(query.getResultList(), category);
    }

    /**
     * 分类排序
     *
     * @param category
     *            分类
     * @param parent
     *            上级分类
     * @return 排序分类
     */
    private List<T> sort(List<T> categories, BaseCategory parent) {
        List<T> result = new ArrayList<>();
        if (categories != null) {
            for (T category : categories) {
                if ((category.getParent() != null && category.getParent().equals(parent)) || (category.getParent() == null && parent == null)) {
                    result.add(category);
                    result.addAll(sort(categories, category));
                }
            }
        }
        return result;
    }

    /**
     * 设置tree、grade并保存
     *
     * @param category
     *            分类
     */
    @Override
    public void insert(T category) {
        setValue(category);
        super.persist(category);
    }

    /**
     * 设置tree、grade并更新
     *
     * @param category
     *            分类
     * @return 分类
     */
    @Override
    public T update(T category) {
        setValue(category);
        for (BaseCategory c : findChildren(category, null)) {
            setValue(c);
        }
        return super.merge(category);
    }

    /**
     * 设置值
     *
     * @param category
     *            分类
     */
    private void setValue(BaseCategory category) {
        if (category == null) {
            return;
        }
        BaseCategory parent = category.getParent();
        if (parent != null) {
            category.setTree(parent.getTree() + parent.getId() + BaseCategory.TREE_PATH_SEPARATOR);
        } else {
            category.setTree(BaseCategory.TREE_PATH_SEPARATOR);
        }
        category.setGrade(category.getTreePaths().size());
    }

    protected TypedQuery<T> getTypedQuery(String format) {
        return createTypedQuery(String.format(format, getEntityName()));
    }
}
