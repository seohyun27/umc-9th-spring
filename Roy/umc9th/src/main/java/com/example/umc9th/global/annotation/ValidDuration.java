package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.DurationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DurationValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDuration {
    //디폴트 메시지 설정
    String message() default "미션 기간은 최소 1일 이상이여야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
