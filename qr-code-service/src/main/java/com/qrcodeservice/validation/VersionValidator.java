package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.VersionConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VersionValidator implements
        ConstraintValidator<VersionConstraint, String> {

    @Override
    public void initialize(VersionConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals("01");
    }

}
