package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PaymentAmountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentAmountValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PaymentAmountConstraint {
    String message() default "Invalid payment amount";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
