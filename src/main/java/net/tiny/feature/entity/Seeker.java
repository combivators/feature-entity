package net.tiny.feature.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.tiny.dao.entity.BaseEntity;
import net.tiny.feature.entity.secure.Personal;

/**
 * Seeker - 求职者
 *
 */
@Entity
@Table(name = "seeker")
public class Seeker extends BaseEntity {

    public static enum Age {
        KID,     //-15
        STUDENT, //16+
        ADULTS,  //18+
        TWENTY,  //20+
        THIRTY,  //30+
        FORTY,   //40+
        FIFTY,   //50+
        SIXTY,   //60+
        RETIRING,//65+
    }

    public static enum UndergraduateType {
        Junior,
        Highschool,
        Vocational,
        University,
        Postgraduate,
        Doctor
    }

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "seekerSequenceGenerator", sequenceName = "seeker_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seekerSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 求职者名 */
    @Column(nullable = false, length = 100)
    private String name;

    /** 略称 */
    @Column(length = 10)
    private String nameAbbr;

    /** 性别 */
    @Column
    private String sex;

    /** 年龄 */
    @Column
    @Enumerated(EnumType.STRING)
    private Age age;

    /** 事业 */
    @Column
    private String career;

    /** 毕业日期 */
    @Column
    private LocalDate graduateDate;

    /** 毕业学校 */
    @Column
    private String graduateSchool;

    /** 学习专业 */
    @Column
    private String specialty;

    /** 毕业文凭 */
    @Column
    private String undergraduateType;

    /** 个人资料 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pid")
    private Personal person;

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
