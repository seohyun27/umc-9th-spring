package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.PerformNotExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PerformNotExistValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExistPerform {
    //디폴트 메시지 설정
    String message() default "이미 추가한 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
