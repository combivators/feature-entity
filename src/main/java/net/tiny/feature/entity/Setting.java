package net.tiny.feature.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity - 系统设置
 *
 */
@Cacheable(true)
@Entity
@Table(name = "setting")
public class Setting implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length=20)
    private String id;

    @Column(name="site_name", nullable=false, unique = true, length=100)
    private String siteName;

    @Column(name="site_url", nullable=false, unique = true, length=100)
    private String siteUrl;

    @Column(name="site_enabled", nullable=false)
    private boolean siteEnabled = true;

    @Column(name="powered_by", nullable=false, length=100)
    private String poweredBy;

    @Column(name="logo", nullable=false, length=100)
    private String logo;

    @Column(name="seo", nullable=false, length=200)
    private String seo;

    @Column(name="address", nullable=false, length=200)
    private String address;

    @Column(name="post", nullable=false, length=200)
    private String post;

    @Column(name="phone", nullable=false, length=100)
    private String phone;

    @Column(name="email", nullable=false, length=100)
    private String email;

    @Column(name="smtp_host", nullable=false, length=100)
    private String smtpHost;

    @Column(name="smtp_port", nullable=false)
    private int smtpPort;

    @Column(name="smtp_user", nullable=false, length=100)
    private String smtpUser;

    @Column(name="smtp_password", nullable=false, length=100)
    private String smtpPassword;

    @Column(name="mail_type", nullable=false, length=200)
    private String mailType = "text/html";

    @Column(nullable=false, length=200)
    private String template;

    @Column(nullable=false, length=10)
    private String jwt = "RS256";

    @Column(name="auth_pattern", length=500)
    private String authPattern;

    @Lob
    @Column(name="public_key", length=100000)
    private byte[] publicKey;

    @Lob
    @Column(name="private_key", length=100000)
    private byte[] privateKey;

    @Column(name="updated", length=1000)
    private String updated;

    @Column(name="updater", nullable=false, length=100)
    private String updater;

    /** 修改日期 */
    @Column(name="modified", nullable = false)
    private LocalDateTime modified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public boolean isSiteEnabled() {
        return siteEnabled;
    }

    public void setSiteEnabled(boolean siteEnabled) {
        this.siteEnabled = siteEnabled;
    }

    public String getPoweredBy() {
        return poweredBy;
    }

    public void setPoweredBy(String poweredBy) {
        this.poweredBy = poweredBy;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUser() {
        return smtpUser;
    }

    public void setSmtpUser(String smtpUser) {
        this.smtpUser = smtpUser;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getAuthPattern() {
        return authPattern;
    }

    public void setAuthPattern(String pattern) {
        this.authPattern = pattern;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }


    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
