package net.tiny.feature.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.tiny.feature.validation.AdValidator;

//Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = AdValidator.class)
public @interface ValidAd {
    String message() default "{net.tiny.feature.validation.Ad.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
