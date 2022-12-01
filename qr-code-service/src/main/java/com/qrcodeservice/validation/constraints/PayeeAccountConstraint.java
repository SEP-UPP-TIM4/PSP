package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PayeeAccountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PayeeAccountValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PayeeAccountConstraint {
    String message() default "Invalid payee bank account";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
