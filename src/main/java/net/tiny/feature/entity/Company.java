package net.tiny.feature.entity;

import java.time.LocalDate;

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
 * Company - 公司
 *
 */
@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "companySequenceGenerator", sequenceName = "company_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companySequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 公司名 */
    @Column(nullable = false, updatable = false, unique = true, length = 100)
    private String name;

    /** 公司名(地域) */
    @Column(length = 100)
    private String nameLocal;

    /** 公司名(英文) */
    @Column(length = 100)
    private String nameEn;

    /** 公司名(略称) */
    @Column(length = 100)
    private String nameAbbr;

    /** 上市市场名称 */
    @Column(name = "market", length = 200)
    private String market;

    /** 证券代码 */
    @Column(name = "scurities_code", length = 200)
    private String scuritiesCode;

    /** 地址 */
    @Column(length = 200)
    private String address;

    /** 邮编 */
    @Column(length = 20)
    private String postalCode;

    /** 纬度 */
    @Column
    private float latitude;

    /** 经度 */
    @Column
    private float longitude;

    /** 电话号码 */
    @Column(length = 20)
    private String tel;

    /** FAX号码 */
    @Column(length = 20)
    private String fax;

    /** 已审查 */
    private boolean review = false;

    /** 可评论 */
    @Column(name = "pickup_review")
    private boolean pickupReview = true;

    /** 平均收入 */
    @Column(name = "avg_income")
    private float avgIncome;

    /** 平均年龄 */
    @Column(name = "avg_age")
    private float avgAge;

    /** 雇员数 */
    @Column
    private int employees;

    /** 成立年份 */
    @Column(name = "establishment_date")
    private LocalDate establishmentDate;

    /** 资本金 */
    @Column
    private float capital;

    /** 股票 */
    @Column(length = 800)
    private String stock;

    /** 从事业务 */
    @Column(length = 800)
    private String business;

    /** 专业 */
    @Column(length = 300)
    private String specialty;

    /** 行业 */
    @Column(length = 100)
    private String industry;

    /** 公司代表者 */
    @Column(name = "president")
    private String president;

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
