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
 * JobCategory - 工作分类
 *
 */
@Entity
@Table(name = "job_category")
@Cacheable(true)
public class JobCategory extends BaseCategory {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "jobCategorySequenceGenerator", sequenceName = "job_category_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobCategorySequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 页面标题 */
    @Column(name = "title", length = 200)
    private String title;

    /** 页面关键词 */
    @Column(length = 200)
    private String keywords;

    /** 上级分类 */
    @ManyToOne(cascade ={ CascadeType.REMOVE })
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private JobCategory parent;

    /** 下级分类 */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("order asc")
    private Set<JobCategory> children = new HashSet<JobCategory>();

    /** 工作 */
    @OneToMany(mappedBy = "jobCategory", fetch = FetchType.LAZY)
    private Set<Job> jobs = new HashSet<Job>();

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
    @Override
    public JobCategory getParent() {
        return parent;
    }

    /**
     * 设置上级分类
     *
     * @param parent
     *            上级分类
     */
    public void setParent(JobCategory parent) {
        this.parent = parent;
    }

    /**
     * 获取下级分类
     *
     * @return 下级分类
     */
    public Set<JobCategory> getChildren() {
        return children;
    }

    /**
     * 设置下级分类
     *
     * @param children
     *            下级分类
     */
    public void setChildren(Set<JobCategory> children) {
        this.children = children;
    }

    /**
     * 获取工作
     *
     * @return 工作
     */
    public Set<Job> getJobs() {
        return jobs;
    }

    /**
     * 设置工作
     *
     * @param jobs
     *            工作
     */
    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
