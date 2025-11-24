package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.CategoryExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategory {
    //디폴트 메시지 설정
    String message() default "해당 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
