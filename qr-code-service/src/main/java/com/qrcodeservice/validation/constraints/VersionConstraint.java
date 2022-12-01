package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.VersionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VersionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface VersionConstraint {
    String message() default "Invalid version";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
