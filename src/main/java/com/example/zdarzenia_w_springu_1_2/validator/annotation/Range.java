package com.example.zdarzenia_w_springu_1_2.validator.annotation;


import com.example.zdarzenia_w_springu_1_2.validator.RangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeValidator.class)
public @interface Range {
    String message() default "Not in range of {min} to {max}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int min() default 0;
    int max() default 10;
}
