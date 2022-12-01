package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.IdentificationCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdentificationCodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IdentificationCodeConstraint {
    String message() default "Invalid identification code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
