package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PaymentPurposeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentPurposeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PaymentPurposeConstraint {
    String message() default "Invalid payment purpose";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
