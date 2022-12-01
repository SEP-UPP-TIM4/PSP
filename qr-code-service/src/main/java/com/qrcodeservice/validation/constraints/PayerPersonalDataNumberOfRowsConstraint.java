package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.PayerPersonalDataNumberOfRowsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PayerPersonalDataNumberOfRowsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PayerPersonalDataNumberOfRowsConstraint {
    String message() default "Invalid payer personal data. Too many rows!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
