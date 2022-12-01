package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PaymentCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentCodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PaymentCodeConstraint {
    String message() default "Invalid payment code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
