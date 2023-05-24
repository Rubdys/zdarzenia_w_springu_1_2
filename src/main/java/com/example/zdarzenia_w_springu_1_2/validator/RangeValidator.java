package com.example.zdarzenia_w_springu_1_2.validator;


import com.example.zdarzenia_w_springu_1_2.validator.annotation.Range;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Integer> {
    private Integer min;
    private Integer max;

    @Override
    public void initialize(Range constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= min && value <= max;
    }
}
