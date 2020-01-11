package net.tiny.feature.entity;

import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import net.tiny.dao.entity.OrderEntity;
import net.tiny.feature.validation.constraints.ValidAd;

/**
 * Ad - 广告
 *
 */
@ValidAd
@Cacheable(true)
@Entity
@Table(name = "ad")
public class Ad extends OrderEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    public enum Type {

        /** 文本 */
        text,

        /** 图片 */
        image,

        /** flash */
        flash
    }

    /** ID */
    @SequenceGenerator(name = "adSequenceGenerator", sequenceName = "ad_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 标题 */
    @NotNull
    @Column(nullable = false, length = 200)
    private String title;

    /** 类型 */
    @NotNull
    @Column(nullable = false)
    private Type type;

    /** 内容 */
    @Lob
    private String content;

    /** 路径 */
    @Column(length = 200)
    private String path;

    /** 起始日期 */
    //@Past //TODO Need to remove
    @Column(name = "begin_date")
    private LocalDateTime beginDate;

    /** 结束日期 */
    //@Future //TODO  Need to remove
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /** 链接地址 */
    @Column(length = 200)
    private String url;

    /** 广告位 */
    @NotNull
    @ManyToOne(cascade ={ CascadeType.REMOVE })
    @JoinColumn(name = "ad_position", nullable = false)
    private AdPosition adPosition;

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
     * 获取标题
     *
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title
     *            标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    public Type getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type
     *            类型
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content
     *            内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取路径
     *
     * @return 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path
     *            路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取起始日期
     *
     * @return 起始日期
     */
    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    /**
     * 设置起始日期
     *
     * @param beginDate
     *            起始日期
     */
    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束日期
     *
     * @return 结束日期
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * 设置结束日期
     *
     * @param endDate
     *            结束日期
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取链接地址
     *
     * @return 链接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接地址
     *
     * @param url
     *            链接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取广告位
     *
     * @return 广告位
     */
    public AdPosition getAdPosition() {
        return adPosition;
    }

    /**
     * 设置广告位
     *
     * @param adPosition
     *            广告位
     */
    public void setAdPosition(AdPosition adPosition) {
        this.adPosition = adPosition;
    }

    /**
     * 判断是否已开始
     *
     * @return 是否已开始
     */
    @Transient
    public boolean hasBegun() {
        return getBeginDate() == null || LocalDateTime.now().isAfter(getBeginDate());
    }

    /**
     * 判断是否已结束
     *
     * @return 是否已结束
     */
    @Transient
    public boolean hasEnded() {
        return getEndDate() != null && LocalDateTime.now().isAfter(getEndDate());
    }

}
