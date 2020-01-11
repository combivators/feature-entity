package net.tiny.feature.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tiny.feature.entity.Ad;
import net.tiny.feature.validation.constraints.ValidAd;

public class AdValidator implements ConstraintValidator<ValidAd, Ad> {

    @Override
    public void initialize(ValidAd constraintAnnotation) {
    }

    @Override
    public boolean isValid(Ad ad, ConstraintValidatorContext context) {
        boolean valid = (ad.getBeginDate() == null && ad.getEndDate() == null);
        if(valid) {
            return valid;
        }
        valid = (ad.getBeginDate() != null && ad.getEndDate() != null &&
                ad.getBeginDate().isBefore(ad.getEndDate()));
        return valid;
    }

}
