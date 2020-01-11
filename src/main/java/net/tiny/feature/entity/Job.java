package net.tiny.feature.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import net.tiny.dao.entity.BaseEntity;

/**
 * Job - 工作
 *
 */
@Entity
@Table(name = "job")
public class Job extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** 点击数缓存名称 */
    public static final String HITS_CACHE_NAME = "jobHits";

    /** 点击数缓存更新间隔时间 */
    public static final int HITS_CACHE_INTERVAL = 600000;

    /** ID */
    @SequenceGenerator(name = "jobSequenceGenerator", sequenceName = "job_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 标题 */
    @Column(nullable = false, length=200)
    private String title;

    /** 内容 */
    @Lob
    private String content;

    /** 页面标题 */
    @Column(name = "seo_title", length = 200)
    private String seoTitle;

    /** 页面关键词 */
    @Column(length = 200)
    private String keywords;

    /** 页面描述 */
    @Column(length=200)
    private String description;

    /** 是否发布 */
    @NotNull
    @Column(nullable = false, length=200)
    private Boolean publication;

    /** 是否置顶 */
    @NotNull
    @Column(nullable = false)
    private Boolean top;

    /** 点击数 */
    @Column(name = "hits", nullable = false)
    private Long hits;

    /** 发布者 */
    @Column(length=200)
    private String author;


    /** 分类 */
    @NotNull
    @ManyToOne(cascade ={ CascadeType.REMOVE })
    @JoinColumn(name = "job_category", referencedColumnName = "id", nullable = false)
    private JobCategory jobCategory;

    /** 标签 */
    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "job_tag"
    , joinColumns = {@JoinColumn(name = "jobs", referencedColumnName="id")}
    , inverseJoinColumns = {@JoinColumn(name = "tags", referencedColumnName="id")}
    )
    @OrderBy("order asc")
    private Set<Tag> tags = new HashSet<Tag>();
    */


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
     * 获取标题
     *
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title
     *            标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取作者
     *
     * @return 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author
     *            作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content
     *            内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取页面标题
     *
     * @return 页面标题
     */
    public String getSeoTitle() {
        return seoTitle;
    }

    /**
     * 设置页面标题
     *
     * @param seoTitle
     *            页面标题
     */
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
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
     * @param seoKeywords
     *            页面关键词
     */
    public void setKeywords(String keywords) {
        if (keywords != null) {
            this.keywords = keywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        }
    }

    /**
     * 获取页面描述
     *
     * @return 页面描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置页面描述
     *
     * @param seoDescription
     *            页面描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取是否发布
     *
     * @return 是否发布
     */
    public Boolean isPublication() {
        return publication;
    }

    /**
     * 设置是否发布
     *
     * @param publication
     *            是否发布
     */
    public void setPublication(Boolean publication) {
        this.publication = publication;
    }

    /**
     * 获取是否置顶
     *
     * @return 是否置顶
     */
    public Boolean isTop() {
        return top;
    }

    /**
     * 设置是否置顶
     *
     * @param isTop
     *            是否置顶
     */
    public void setTop(Boolean top) {
        this.top = top;
    }

    /**
     * 获取点击数
     *
     * @return 点击数
     */
    public Long getHits() {
        return hits;
    }

    /**
     * 设置点击数
     *
     * @param hits
     *            点击数
     */
    public void setHits(Long hits) {
        this.hits = hits;
    }

    /**
     * 获取分类
     *
     * @return 分类
     */
    public JobCategory getJobCategory() {
        return jobCategory;
    }

    /**
     * 设置分类
     *
     * @param jobCategory
     *            分类
     */
    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

}
