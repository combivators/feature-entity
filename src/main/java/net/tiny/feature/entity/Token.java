package net.tiny.feature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity - 令牌
 *
 */
@Entity
@Table(name = "token")
public class Token {

    @Transient
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    public static enum Type {

        /** 管理员 */
        admin,

        /** 商品标签 */
        member
    };

    @Id
    @Column(name = "id", length=100)
    private String id;

    /** 类型 */
    @Column(nullable = false, updatable = false)
    private Type type = Type.member;

    @OneToOne(mappedBy = "token")
    private Admin admin;

    @OneToOne(mappedBy = "token")
    private Account account;

    /**
     * 获取ID
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id
     *            ID
     */
    public void setId(String id) {
        this.id = id;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
