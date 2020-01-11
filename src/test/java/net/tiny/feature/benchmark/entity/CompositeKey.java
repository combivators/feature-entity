package net.tiny.feature.benchmark.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class CompositeKey implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    //50维
    float v001;
    float v002;
    float v003;
    float v004;
    float v005;
    float v006;
    float v007;
    float v008;
    float v009;
    float v010;
    float v011;
    float v012;
    float v013;
    float v014;
    float v015;
    float v016;
    float v017;
    float v018;
    float v019;
    float v020;
    float v021;
    float v022;
    float v023;
    float v024;
    float v025;
    float v026;
    float v027;
    float v028;
    float v029;
    float v030;
    float v031;
    float v032;
    float v033;
    float v034;
    float v035;
    float v036;
    float v037;
    float v038;
    float v039;
    float v040;
    float v041;
    float v042;
    float v043;
    float v044;
    float v045;
    float v046;
    float v047;
    float v048;
    float v049;
    float v050;

    @Override
    public int hashCode() {
        return new Float(v001).hashCode();//TODO
    }
    @Override
    public boolean equals(Object o) {
        return (o instanceof CompositeKey);//TODO
    }
}
