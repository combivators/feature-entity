package net.tiny.feature.benchmark.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Values - 测试用超多维数据集
 *
 */
@Entity
@IdClass(CompositeKey.class)
@Table(name = "test_values")
public class Values implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;

    /** ID */
    @SequenceGenerator(name = "benchSequenceGenerator", sequenceName = "bench_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "benchSequenceGenerator")
    @Id
    @Column(name = "id")
    private Long id;
    //50维
    @Id float v001;
    @Id float v002;
    @Id float v003;
    @Id float v004;
    @Id float v005;
    @Id float v006;
    @Id float v007;
    @Id float v008;
    @Id float v009;
    @Id float v010;
    @Id float v011;
    @Id float v012;
    @Id float v013;
    @Id float v014;
    @Id float v015;
    @Id float v016;
    @Id float v017;
    @Id float v018;
    @Id float v019;
    @Id float v020;
    @Id float v021;
    @Id float v022;
    @Id float v023;
    @Id float v024;
    @Id float v025;
    @Id float v026;
    @Id float v027;
    @Id float v028;
    @Id float v029;
    @Id float v030;
    @Id float v031;
    @Id float v032;
    @Id float v033;
    @Id float v034;
    @Id float v035;
    @Id float v036;
    @Id float v037;
    @Id float v038;
    @Id float v039;
    @Id float v040;
    @Id float v041;
    @Id float v042;
    @Id float v043;
    @Id float v044;
    @Id float v045;
    @Id float v046;
    @Id float v047;
    @Id float v048;
    @Id float v049;
    @Id float v050;
}
