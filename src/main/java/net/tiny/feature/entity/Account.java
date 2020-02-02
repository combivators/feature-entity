package net.tiny.feature.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import net.tiny.dao.entity.BaseEntity;

/**
 * Entity - 认证账号
 *
 */
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "accountSequenceGenerator", sequenceName = "account_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 用户名 */
    @Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
    @Column(nullable = false, updatable = false, unique = true, length = 100)
    private String username;

    /** 密码 */
    @Pattern(regexp = "^[^\\s&\"<>]+$")
    //@Length(min = 4, max = 20)
    @Column(nullable = false)
    private String password;

    /** E-mail */
    @Column(nullable = false, length = 200)
    private String email;

    /** 表示名 */
    @Column(length = 200)
    private String name;

    /** 用户组 */
//    @Column(length = 200)
//    private String group;

    /** 是否启用 */
    @NotNull
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = true;

    /** 是否锁定 */
    @Column(name = "is_locked", nullable = false)
    private Boolean isLocked = false;

    /** 连续登录失败次数 */
    @Column(name = "login_failure_count", nullable = false)
    private Integer loginFailureCount;

    /** 锁定日期 */
    @Column(name = "locked_date")
    private LocalDateTime lockedDate;

    /** 最后登录日期 */
    @Column(name = "login_date")
    private LocalDateTime loginDate;

    /** 最后登录IP */
    @Column(name = "login_ip")
    private String loginIp;

    /** 用户分组 */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="account_group"
        , joinColumns = {@JoinColumn(name = "accounts", referencedColumnName = "id")}
        , inverseJoinColumns = {@JoinColumn(name = "groups", referencedColumnName = "id")}
        , uniqueConstraints = {@UniqueConstraint(columnNames = {"accounts", "groups"})}
    )
    private Set<Group> groups = new HashSet<Group>();

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
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username
     *            用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password
     *            密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取E-mail
     *
     * @return E-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置E-mail
     *
     * @param email
     *            E-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取姓名
     *
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name
     *            姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户组
     *
     * @return 用户组门
     */
//    public String getGroup() {
//        return group;
//    }

    /**
     * 设置用户组
     *
     * @param group
     *            用户组
     */
//    public void setGroup(String group) {
//        this.group = group;
//    }

    /**
     * 获取是否启用
     *
     * @return 是否启用
     */
    public boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置是否启用
     *
     * @param isEnabled
     *            是否启用
     */
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 获取是否锁定
     *
     * @return 是否锁定
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     * 设置是否锁定
     *
     * @param isLocked
     *            是否锁定
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * 获取连续登录失败次数
     *
     * @return 连续登录失败次数
     */
    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    /**
     * 设置连续登录失败次数
     *
     * @param loginFailureCount
     *            连续登录失败次数
     */
    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    /**
     * 获取锁定日期
     *
     * @return 锁定日期
     */
    public LocalDateTime getLockedDate() {
        return lockedDate;
    }

    /**
     * 设置锁定日期
     *
     * @param lockedDate
     *            锁定日期
     */
    public void setLockedDate(LocalDateTime lockedDate) {
        this.lockedDate = lockedDate;
    }

    /**
     * 获取最后登录日期
     *
     * @return 最后登录日期
     */
    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    /**
     * 设置最后登录日期
     *
     * @param loginDate
     *            最后登录日期
     */
    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 获取最后登录IP
     *
     * @return 最后登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置最后登录IP
     *
     * @param loginIp
     *            最后登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取用户分组
     *
     * @return 用户分组
     */
    public Set<Group> getGroups() {
        return groups;
    }

    /**
     * 设置用户分组
     *
     * @param groups
     *            用户分组
     */
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    /**
     * 获取角色
     *
     * @return 角色
     */
    public String getAuthorities() {
        StringBuilder sb = new StringBuilder();
        for(Group group : groups) {
            List<String> list = group.getAuthorities();
            for(String auth : list) {
                if(sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(auth);
            }
        }
        return sb.toString();
    }

}
