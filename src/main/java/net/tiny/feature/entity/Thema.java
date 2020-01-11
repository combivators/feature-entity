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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.tiny.dao.entity.OrderEntity;

/**
 * Thema - 主题
 *
 */
@Entity
@Table(name = "thema")
@Cacheable(true)
public class Thema extends OrderEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "themaSequenceGenerator", sequenceName = "thema_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "themaSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 名称 */
    @Column(nullable = false, length = 200)
    private String name;

    /** 图标 */
    @Column(length = 200)
    private String image;

    /** 备注 */
    @Column(length = 200)
    private String summary;

    /** 文章 */
    @ManyToMany(mappedBy = "themas", fetch = FetchType.LAZY)
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
     * 获取图标
     *
     * @return 图标
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图标
     *
     * @param icon
     *            图标
     */
    public void SetImage(String image) {
        this.image = image;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置备注
     *
     * @param memo
     *            备注
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
