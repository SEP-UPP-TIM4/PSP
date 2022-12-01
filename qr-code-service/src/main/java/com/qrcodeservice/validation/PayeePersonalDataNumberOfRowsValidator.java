package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PayeePersonalDataNumberOfRowsConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PayeePersonalDataNumberOfRowsValidator implements
        ConstraintValidator<PayeePersonalDataNumberOfRowsConstraint, String> {

    @Override
    public void initialize(PayeePersonalDataNumberOfRowsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.split("\r\n|\r|\n").length > 3)
            return false;
        return true;
    }
}
