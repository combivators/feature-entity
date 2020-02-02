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
 * Entity - 角色
 *
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "roleSequenceGenerator", sequenceName = "role_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 名称 */
    @Column(nullable = false, length = 200)
    private String name;

    /** 是否内置 */
    @Column(name="is_system", nullable = false, updatable = false)
    private Boolean isSystem;

    /** 描述 */
    @Column(length = 200)
    private String description;

    /** 权限 */
    @ElementCollection
    @CollectionTable(name = "role_authority"
        , joinColumns={@JoinColumn(name="role",  referencedColumnName="id")}
        )
    private List<String> authorities = new ArrayList<String>();

    /** 管理员 */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Admin> admins = new HashSet<Admin>();

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
     * 获取是否内置
     *
     * @return 是否内置
     */
    public Boolean getIsSystem() {
        return isSystem;
    }

    /**
     * 设置是否内置
     *
     * @param isSystem
     *            是否内置
     */
    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
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
     * 获取管理员
     *
     * @return 管理员
     */
    public Set<Admin> getAdmins() {
        return admins;
    }

    /**
     * 设置管理员
     *
     * @param admins
     *            管理员
     */
    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

}
