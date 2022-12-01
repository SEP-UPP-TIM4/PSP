package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PayeePersonalDataLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PayeePersonalDataLengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PayeePersonalDataLengthConstraint {
    String message() default "Invalid payee personal data. Too many characters!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
