package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.IdentificationCodeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificationCodeValidator implements
        ConstraintValidator<IdentificationCodeConstraint, String> {

    @Override
    public void initialize(IdentificationCodeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals("PR");
    }


}
