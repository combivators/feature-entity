package net.tiny.feature.entity.secure;

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
 * Contact - 联系方式※隐私保护
 *
 */
@Entity
@Table(name = "contact")
public class Contact extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "contactSequenceGenerator", sequenceName = "contact_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 地址 */
    @Column(length = 200)
    private String address;

    /** 邮编 */
    @Column(length = 20)
    private String postalCode;

    /** 电话号码 */
    @Column(length = 20)
    private String tel;

    /** FAX号码 */
    @Column(length = 20)
    private String fax;

    /** 无效 */
    @Column
    private boolean disable = false;

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
