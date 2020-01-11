package net.tiny.feature.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * ArticleCategory - 文章分类
 *
 */
@Entity
@Table(name = "article_category")
@Cacheable(true)
public class ArticleCategory extends BaseCategory {

    @Transient
    private static final long serialVersionUID = 1L;

    /** 访问路径前缀 */
    //private static final String PATH_PREFIX = "/article/list";

    /** 访问路径后缀 */
    //private static final String PATH_SUFFIX = "";

    /** ID */
    @SequenceGenerator(name = "articleCategorySequenceGenerator", sequenceName = "article_category_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articleCategorySequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 页面标题 */
    @Column(length = 200)
    private String title;

    /** 页面关键词 */
    @Column(length = 200)
    private String keywords;

    /** 上级分类 */
    @ManyToOne(cascade ={ CascadeType.REMOVE })
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private ArticleCategory parent;

    /** 下级分类 */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @OrderBy("order asc")
    private Set<ArticleCategory> children = new HashSet<ArticleCategory>();

    /** 文章 */
    @OneToMany(mappedBy = "articleCategory", fetch = FetchType.LAZY)
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
     * 获取页面标题
     *
     * @return 页面标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置页面标题
     *
     * @param title
     *            页面标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取页面关键词
     *
     * @return 页面关键词
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置页面关键词
     *
     * @param keywords
     *            页面关键词
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取上级分类
     *
     * @return 上级分类
     */
    public ArticleCategory getParent() {
        return parent;
    }

    /**
     * 设置上级分类
     *
     * @param parent
     *            上级分类
     */
    public void setParent(ArticleCategory parent) {
        this.parent = parent;
    }

    /**
     * 获取下级分类
     *
     * @return 下级分类
     */
    public Set<ArticleCategory> getChildren() {
        return children;
    }

    /**
     * 设置下级分类
     *
     * @param children
     *            下级分类
     */
    public void setChildren(Set<ArticleCategory> children) {
        this.children = children;
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
}
