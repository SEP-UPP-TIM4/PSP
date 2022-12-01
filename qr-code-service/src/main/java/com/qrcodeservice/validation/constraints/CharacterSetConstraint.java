package com.qrcodeservice.validation.constraints;

import com.qrcodeservice.validation.CharacterSetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CharacterSetValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CharacterSetConstraint {
    String message() default "Invalid character set";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
