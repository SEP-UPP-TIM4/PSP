package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PayerPersonalDataLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PayerPersonalDataLengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PayerPersonalDataLengthConstraint {
    String message() default "Invalid payer personal data. Too many characters!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
