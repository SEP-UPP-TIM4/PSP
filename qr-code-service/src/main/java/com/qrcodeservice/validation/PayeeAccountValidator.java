package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PayeeAccountConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PayeeAccountValidator implements
        ConstraintValidator<PayeeAccountConstraint, String> {

    @Override
    public void initialize(PayeeAccountConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length() != 18)
            return false;
        for(char c : s.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
