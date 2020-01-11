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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.tiny.dao.entity.BaseEntity;

/**
 * Entity - 广告位
 *
 */
@Cacheable(true)
@Entity
@Table(name = "ad_position")
public class AdPosition extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** 缓存名称 */
    public static final String CACHE_NAME = AdPosition.class.getName();

    /** 缓存更新间隔时间 */
    public static final int CACHE_INTERVAL = 600000;

    /** ID */
    @SequenceGenerator(name = "adPositionSequenceGenerator", sequenceName = "ad_position_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adPositionSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 名称 */
    @NotNull
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String name;

    /** 宽度 */
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer width;

    /** 高度 */
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer height;

    /** 描述 */
    @Size(max = 200)
    @Column(length = 200)
    private String description;

    /** 模板 */
    @Size(max = 1024)
    @Column(nullable = false, length = 1024)
    private String template;

    /** 广告 */
    @OrderBy("order asc")
    @OneToMany(mappedBy = "adPosition", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)  //映射为单一对多关系
    //@JoinColumn(name="id")
    private Set<Ad> ads = new HashSet<Ad>();
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
     * 获取宽度
     *
     * @return 宽度
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置宽度
     *
     * @param width
     *            宽度
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 获取高度
     *
     * @return 高度
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 设置高度
     *
     * @param height
     *            高度
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 获取描述
     *
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description
     *            描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取模板
     *
     * @return 模板
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 设置模板
     *
     * @param template
     *            模板
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * 获取广告
     *
     * @return 广告
     */
    public Set<Ad> getAds() {
        return ads;
    }

    /**
     * 设置广告
     *
     * @param ads
     *            广告
     */
    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }

}
