package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PayeePersonalDataLengthConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PayeePersonalDataLengthValidator implements
        ConstraintValidator<PayeePersonalDataLengthConstraint, String> {

    @Override
    public void initialize(PayeePersonalDataLengthConstraint constraintAnnotation) {
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
