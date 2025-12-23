package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.ManagerPermissionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ManagerPermissionValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ManagerPermission {
    //디폴트 메시지 설정
    String message() default "매니저 권한이 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
