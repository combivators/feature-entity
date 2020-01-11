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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.tiny.dao.entity.OrderEntity;

/**
 *
 * ReviewKeywordCategory - 审核关键字分类
 *
 */
@Cacheable(true)
@Entity
@Table(name = "review_keyword_category")
public class ReviewKeywordCategory extends OrderEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "reviewKeywordCategorySequenceGenerator", sequenceName = "review_keyword_category_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewKeywordCategorySequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 名称 */
    @NotNull
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String name;

    /** 分类 */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<ReviewKeyword> keywords = new HashSet<ReviewKeyword>();

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
     * 获取关键词
     *
     * @return 关键词
     */
    public Set<ReviewKeyword> getKeywords() {
        return keywords;
    }

    /**
     * 设置关键词
     *
     * @param keywords
     *            关键词
     */
    public void gsetKeywords(Set<ReviewKeyword> keywords) {
        this.keywords = keywords;
    }
}
