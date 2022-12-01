package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.ModelAndReferenceNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ModelAndReferenceNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelAndReferenceNumberConstraint {
    String message() default "Invalid model and reference number.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
