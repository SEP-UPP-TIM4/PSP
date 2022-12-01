package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PayerPersonalDataNumberOfRowsConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PayerPersonalDataNumberOfRowsValidator implements
        ConstraintValidator<PayerPersonalDataNumberOfRowsConstraint, String> {

    @Override
    public void initialize(PayerPersonalDataNumberOfRowsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.split("\r\n|\r|\n").length > 3)
            return false;
        return true;
    }
}
