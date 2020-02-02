package net.tiny.feature.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.tiny.dao.entity.BaseEntity;

/**
 * Entity - 用户分组
 *
 */
@Entity
@Table(name = "account_group")
public class Group extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "groupSequenceGenerator", sequenceName = "group_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 名称 */
    @Column(nullable = false, length = 200)
    private String name;

    /** 描述 */
    @Column(length = 200)
    private String description;

    /** 分组权限 */
    @ElementCollection
    @CollectionTable(name = "group_authority"
        , joinColumns={@JoinColumn(name="gid",  referencedColumnName="id")}
        )
    private List<String> authorities = new ArrayList<String>();

    /** 管理员 */
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<Account>();

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
     * 获取权限
     *
     * @return 权限
     */
    public List<String> getAuthorities() {
        return authorities;
    }

    /**
     * 设置权限
     *
     * @param authorities
     *            权限
     */
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    /**
     * 获取认证账号
     *
     * @return 认证账号
     */
    public Set<Account> getAccounts() {
        return accounts;
    }

    /**
     * 设置认证账号
     *
     * @param accounts
     *            认证账号
     */
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
