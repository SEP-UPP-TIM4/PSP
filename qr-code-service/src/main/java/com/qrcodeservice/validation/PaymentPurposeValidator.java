package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PaymentPurposeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PaymentPurposeValidator implements
        ConstraintValidator<PaymentPurposeConstraint, String> {

    @Override
    public void initialize(PaymentPurposeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.equals(""))
            return true;
        if(s.split("\r\n|\r|\n").length > 1)
            return false;
        if(s.length() > 35)
            return false;
        return true;
    }
}
