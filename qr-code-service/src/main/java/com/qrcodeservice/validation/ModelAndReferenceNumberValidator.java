package com.qrcodeservice.validation;

import com.qrcodeservice.validation.constraints.ModelAndReferenceNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelAndReferenceNumberValidator implements
        ConstraintValidator<ModelAndReferenceNumberConstraint, String> {

    @Override
    public void initialize(ModelAndReferenceNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.equals(""))
            return true;
        if(s.length() > 25)
            return false;
        Pattern p = Pattern.compile("[0-9]{2}[0-9|-]+");
        Matcher m = p.matcher(s);
        if(s.contains("-") && s.charAt(0) == '9' && s.charAt(1) == '7')
            return false;
        if(m.matches())
            return true;
        return false;
    }
}
