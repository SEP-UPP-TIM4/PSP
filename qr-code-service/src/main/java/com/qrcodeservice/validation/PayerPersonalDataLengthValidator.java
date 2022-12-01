package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PayerPersonalDataLengthConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PayerPersonalDataLengthValidator implements
        ConstraintValidator<PayerPersonalDataLengthConstraint, String> {

    @Override
    public void initialize(PayerPersonalDataLengthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        s.replace("\n", "").replace("\r", "");
        if(s.length() > 70)
            return false;

        return true;
    }
}
