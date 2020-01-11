package net.tiny.feature.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import net.tiny.dao.entity.OrderEntity;

/**
 * Tag - 标签
 *
 */
@Entity
@Table(name = "tag")
@Cacheable(true)
public class Tag extends OrderEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    public static enum Type {

        /** 文章标签 */
        article,

        /** 商品标签 */
        product,

        /** 工作标签 */
        job
    };

    /** ID */
    @SequenceGenerator(name = "tagSequenceGenerator", sequenceName = "tag_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 名称 */
    @Column(nullable = false, length=200)
    private String name;

    /** 类型 */
    @NotNull(groups = Save.class)
    @Column(nullable = false, updatable = false)
    private Type type;

    /** 图标 */
    @Column(length = 200)
    private String icon;

    /** 备注 */
    @Column(length = 200)
    private String memo;

    /** 文章 */
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Article> articles = new HashSet<Article>();

    /**
     * 获取ID
     *
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id
     *            ID
     */
    public void setId(Long id) {
        this.id = id;
    }


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
     * 获取类型
     *
     * @return 类型
     */
    public Type getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type
     *            类型
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 获取图标
     *
     * @return 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon
     *            图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo
     *            备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取文章
     *
     * @return 文章
     */
    public Set<Article> getArticles() {
        return articles;
    }

    /**
     * 设置文章
     *
     * @param articles
     *            文章
     */
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    /**
     * 删除前处理
     */
    @PreRemove
    public void preRemove() {
        Set<Article> articles = getArticles();
        if (articles != null) {
            for (Article article : articles) {
                article.getTags().remove(this);
            }
        }
    }

}
