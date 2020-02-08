package net.tiny.feature.entity.secure;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import net.tiny.feature.entity.Account;

/**
 * Entity - 电话
 *
 */
@Entity
@Table(name = "phone")
public class Phone {

    @Transient
    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "^[0-9]+$")
    @Id
    @Column(name = "number", length=100)
    private String number;

    /** 令牌 */
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
