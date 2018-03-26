package com.team7.cfs.util;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberFormatCheckImpl.class)
@Documented
public @interface NumberFormatCheck {
    String message() default "Number must be in #.## format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
