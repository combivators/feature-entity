package net.tiny.feature.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;

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

	@NotNull
	@Column(name="site_name", nullable=false, length=100)
    private String siteName;

	@NotNull
	@Column(name="site_url", nullable=false, length=100)
    private String siteUrl;

	@NotNull
	@Column(name="site_enabled", nullable=false)
    private boolean siteEnabled = true;

	@NotNull
	@Column(name="powered_by", nullable=false, length=100)
    private String poweredBy;

	@NotNull
	@Column(name="logo", nullable=false, length=100)
    private String logo;

	@NotNull
	@Column(name="seo", nullable=false, length=200)
    private String seo;

	@NotNull
	@Column(name="address", nullable=false, length=200)
    private String address;

	@NotNull
	@Column(name="post", nullable=false, length=200)
    private String post;

	@NotNull
	@Column(name="phone", nullable=false, length=100)
    private String phone;

	@NotNull
	@Column(name="email", nullable=false, length=100)
    private String email;

	@NotNull
	@Column(name="smtp_host", nullable=false, length=100)
    private String smtpHost;

	@NotNull
	@Column(name="smtp_port", nullable=false)
    private int smtpPort;

	@NotNull
	@Column(name="smtp_user", nullable=false, length=100)
    private String smtpUser;

	@NotNull
	@Column(name="smtp_password", nullable=false, length=100)
    private String smtpPassword;

	@NotNull
	@Column(name="public_key", nullable=false, length=1000)
    private String publicKey;

	@NotNull
	@Column(name="private_key", nullable=false, length=1000)
    private String privateKey;
}
