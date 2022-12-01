package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.CharacterSetConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CharacterSetValidator implements
        ConstraintValidator<CharacterSetConstraint, String> {
    @Override
    public void initialize(CharacterSetConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals("1");
    }
}
