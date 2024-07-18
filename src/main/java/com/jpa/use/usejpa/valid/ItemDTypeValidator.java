package com.jpa.use.usejpa.valid;

import com.jpa.use.usejpa.domain.enumerate.ItemDType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ItemDTypeValidator implements ConstraintValidator<ValidItemDType, Class<?>> {
    @Override
    public boolean isValid(Class<?> value, ConstraintValidatorContext context) {
        return ItemDType.valid(value);
    }

    @Override
    public void initialize(ValidItemDType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
