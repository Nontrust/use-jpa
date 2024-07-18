package com.jpa.use.usejpa.valid;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemDTypeValidator.class)
public @interface ValidItemDType {
    String message() default "잘못된 Enum 값 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
