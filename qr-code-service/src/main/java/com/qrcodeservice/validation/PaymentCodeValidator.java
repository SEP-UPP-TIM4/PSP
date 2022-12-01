package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.PaymentCodeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentCodeValidator implements
        ConstraintValidator<PaymentCodeConstraint, String> {

    @Override
    public void initialize(PaymentCodeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern p = Pattern.compile("[1|2]{1}[0-9]{2}");
        Matcher m = p.matcher(s);
        if(m.matches())
            return true;
        return false;
    }
}
