package net.tiny.feature.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import net.tiny.dao.entity.OrderEntity;

/**
 * Entity - 分类基类
 *
 */
@MappedSuperclass
public abstract class BaseCategory extends OrderEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    public static enum Type {
        root, parent, child
    }

    /** 树路径分隔符 */
    public static final String TREE_PATH_SEPARATOR = ",";

    public abstract BaseCategory getParent();

    /** 名称 */
    @Column(nullable = false, length=100)
    private String name;

    /** 标签 */
    @Column(length=100)
    private String label;

    /** 摘要 */
    @Column(length=200)
    private String summary;

    /** 层级 */
    @Column(nullable = false)
    private Integer grade;

    /** 树路径 */
    @Column(nullable = false)
    private String tree;

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name
     *            名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取标签
     *
     * @return 标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置标签
     *
     * @param label
     *            标签
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取摘要
     *
     * @return 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary
     *            摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取层级
     *
     * @return 层级
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置层级
     *
     * @param grade
     *            层级
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取树路径
     *
     * @return 树路径
     */
    public String getTree() {
        return tree;
    }

    /**
     * 设置树路径
     *
     * @param tree
     *            树路径
     */
    public void setTree(String tree) {
        this.tree = tree;
    }

    /**
     * 获取树路径
     *
     * @return 树路径
     */
    public List<Long> getTreePaths() {
        List<Long> treePaths = new ArrayList<Long>();
        String[] ids = getTree().split(TREE_PATH_SEPARATOR);
        if (ids != null) {
            for (String id : ids) {
                if(!id.isEmpty()) {
                    treePaths.add(Long.valueOf(id));
                }
            }
        }
        return treePaths;
    }
}
