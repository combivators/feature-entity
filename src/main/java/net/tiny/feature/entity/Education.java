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
 * Education - 教育背景
 *
 */
@Entity
@Table(name = "education")
public class Education extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "educationSequenceGenerator", sequenceName = "education_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "educationSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 姓名 */
    @Column(nullable = false, length = 100)
    private String name;

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
