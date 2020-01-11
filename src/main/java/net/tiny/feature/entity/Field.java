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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Field - 领域
 *
 */
@Entity
@Table(name = "field")
@Cacheable(true)
public class Field extends BaseCategory {

    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "fieldSequenceGenerator", sequenceName = "field_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fieldSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;

    /** 上级分类 */
    @ManyToOne(cascade ={ CascadeType.REMOVE })
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private Field parent;

    /** 下级分类 */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("order asc")
    private Set<Field> children = new HashSet<>();

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
     * 获取上级分类
     *
     * @return 上级分类
     */
    @Override
    public Field getParent() {
        return parent;
    }

    /**
     * 设置上级分类
     *
     * @param parent
     *            上级分类
     */
    public void setParent(Field parent) {
        this.parent = parent;
    }

    /**
     * 获取下级分类
     *
     * @return 下级分类
     */
    public Set<Field> getChildren() {
        return children;
    }

    /**
     * 设置下级分类
     *
     * @param children
     *            下级分类
     */
    public void setChildren(Set<Field> children) {
        this.children = children;
    }

}
