package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PaymentAmountConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentAmountValidator implements
        ConstraintValidator<PaymentAmountConstraint, String> {

    @Override
    public void initialize(PaymentAmountConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern p = Pattern.compile("RSD[0-9]+,[0-9]*{5,18}");
        Matcher m = p.matcher(s);
        if(m.matches())
            return true;
        return false;
    }
}
