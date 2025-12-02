package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.CategoryExistValidator;
import com.example.umc9th.global.validator.StoreExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {
    //디폴트 메시지 설정
    String message() default "해당 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
