package net.tiny.feature.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import net.tiny.dao.BaseDao;
import net.tiny.dao.Filter;
import net.tiny.dao.Order;
import net.tiny.dao.Page;
import net.tiny.dao.Pageable;
import net.tiny.feature.entity.Article;
import net.tiny.feature.entity.ArticleCategory;
import net.tiny.feature.entity.Tag;

/**
 * ArticleDao - 文章
 *
 */
public class ArticleDao extends BaseDao<Article, Long> {

    /**
     * 查找文章
     *
     * @param articleCategory
     *            文章分类
     * @param tags
     *            标签
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @return 文章
     */
    public List<Article> findList(ArticleCategory articleCategory, List<Tag> tags, Integer count, List<Filter> filters, List<Order> orders) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
        if (articleCategory != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory), criteriaBuilder.like(root.get("articleCategory").<String> get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
        }
        if (tags != null && !tags.isEmpty()) {
            //标签副查询
            Subquery<Article> subquery = criteriaQuery.subquery(Article.class);
            Root<Article> subqueryRoot = subquery.from(Article.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        criteriaQuery.where(restrictions);
        //置顶
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
        return super.findList(criteriaQuery, null, count, filters, orders);
    }

    /**
     * 查找文章
     *
     * @param articleCategory
     *            文章分类
     * @param beginDate
     *            起始日期
     * @param endDate
     *            结束日期
     * @param first
     *            起始记录
     * @param count
     *            数量
     * @return 文章
     */
    public List<Article> findList(ArticleCategory articleCategory, LocalDate beginDate, LocalDate endDate, Integer first, Integer count) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        //已发布
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
        if (articleCategory != null) {
            //分类和子分类
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory), criteriaBuilder.like(root.get("articleCategory").<String> get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
        }
        if (beginDate != null) {
            LocalDateTime beginTime = beginDate.atStartOfDay();
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<LocalDateTime> get("createDate"), beginTime));
        }
        if (endDate != null) {
            LocalDateTime endTime = endDate.atTime(23, 59, 59);
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<LocalDateTime> get("createDate"), endTime));
        }
        criteriaQuery.where(restrictions);
        //置顶
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
        return super.findList(criteriaQuery, first, count, null, null);
    }
    /**
     * 查找文章分页
     *
     * @param articleCategory
     *            文章分类
     * @param tags
     *            标签
     * @param pageable
     *            分页信息
     * @return 文章分页
     */
    public Page<Article> findPage(ArticleCategory articleCategory, List<Tag> tags, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        //已发布
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
        if (articleCategory != null) {
            //分类和子分类
            restrictions =
                    criteriaBuilder.and(restrictions,
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory),
                            criteriaBuilder.like(root.get("articleCategory").<String> get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
        }
        if (tags != null && !tags.isEmpty()) {
            //标签副查询
            Subquery<Article> subquery = criteriaQuery.subquery(Article.class);
            Root<Article> subqueryRoot = subquery.from(Article.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        criteriaQuery.where(restrictions);
        //置顶
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
        return super.findPage(criteriaQuery, pageable);
    }
}
