package net.tiny.feature.entity.secure;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.tiny.dao.entity.BaseEntity;

/**
 * Personal - 个人资料
 *
 */
@Entity
@Table(name = "personal")
public class Personal extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "personalSequenceGenerator", sequenceName = "personal_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personalSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 姓名 */
    @Column(nullable = false, length = 100)
    private String name;

    /** 姓 */
    @Column
    private String firstName;

    /** 名 */
    @Column
    private String lastName;

    /** 本姓 */
    @Column
    private String firstLocal;

    /** 本名 */
    @Column
    private String lastLocal;

    /** 略称 */
    @Column(length = 10)
    private String nameAbbr;

    /** 性别 */
    @Column
    private String sex;

    /** 生日 */
    @Column
    private LocalDate birth;

    /** 国籍 */
    @Column
    private String nationality;

    /** 婚姻 */
    @Column
    private String civil;

    /** 签证 */
    @Column
    private String visa;

    /** 事业 */
    @Column
    private String career;

    /** 宗教 */
    //@Column
    //private String religion;

    /** 联系方式 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact")
    private Contact contact;

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
