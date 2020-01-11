package net.tiny.feature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.tiny.dao.entity.BaseEntity;

/**
 * Author - 文章作者
 *
 */
@Entity
@Table(name = "author")
public class Author extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "authorSequenceGenerator", sequenceName = "author_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;


    /** 作者笔名 */
    @Column(nullable = false, updatable = false, length = 30)
    private String name;

    /** 作者本名(地域) */
    @Column(length = 30)
    private String nameLocal;

    /** 图标 */
    @Column(length = 200)
    private String image;

    /** 作者简介 */
    @Column(length = 300)
    private String profile;

    /** 外部链接标题 */
    @Column(name = "link_title", length = 100)
    private String linkTitle;

    /** 外部链接 */
    @Column(length = 100)
    private String link;

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
}
