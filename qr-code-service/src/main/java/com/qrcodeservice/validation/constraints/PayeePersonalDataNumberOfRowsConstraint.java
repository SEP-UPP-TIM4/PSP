package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PayeePersonalDataNumberOfRowsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PayeePersonalDataNumberOfRowsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PayeePersonalDataNumberOfRowsConstraint {
    String message() default "Invalid payee personal data. Too many rows!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
