package net.tiny.feature.entity;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import net.tiny.dao.entity.BaseEntity;

/**
*
* ReviewKeyword - 评价关键词
*
*/
@Cacheable(true)
@Entity
@Table(name = "review_keyword")
public class ReviewKeyword extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "reviewKeywordSequenceGenerator", sequenceName = "review_keyword_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewKeywordSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 分类 */
    @NotNull
    @ManyToOne(cascade ={ CascadeType.REMOVE })
    @JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
    private ReviewKeywordCategory category;

    /** 关键词 */
    @Column(nullable = false, length=100)
    private String keyword;

    /** 评价好坏 */
    @Column(nullable = false)
    private Boolean good;

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
     * 获取关键词
     *
     * @return 关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键词
     *
     * @param keyword
     *            关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    /**
     * 获取评价
     *
     * @return 评价好坏
     */
    public Boolean isGood() {
        return good;
    }

    /**
     * 设置评价好坏
     *
     * @param good
     *            评价好坏
     */
    public void setGood(Boolean good) {
        this.good = good;
    }

    /**
     * 获取分类
     *
     * @return 分类
     */
    public ReviewKeywordCategory getCategory() {
        return category;
    }

    /**
     * 设置分类
     *
     * @param category
     *            分类
     */
    public void setCategory(ReviewKeywordCategory category) {
        this.category = category;
    }
}
