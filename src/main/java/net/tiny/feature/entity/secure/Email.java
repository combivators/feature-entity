package net.tiny.feature.entity.secure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import net.tiny.feature.entity.Account;

/**
 * Entity - 邮箱
 *
 */
@Entity
@Table(name = "email")
public class Email {

    @Transient
    private static final long serialVersionUID = 1L;

    //@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
    @Id
    @Column(name = "address", length=100)
    private String address;

    @Column(length=100)
    private String lastest;

    /** 验证码 */
    @Column(length=10)
    private String code;

    /** 是否启用 */
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = false;

    /** 令牌 */
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastest() {
        return lastest;
    }

    public void setLastest(String lastest) {
        this.lastest = lastest;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
