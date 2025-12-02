package com.example.umc9th.global.validator;

import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.global.annotation.ValidDuration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class DurationValidator implements ConstraintValidator<ValidDuration, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime endDateTime, ConstraintValidatorContext context) {
        if (endDateTime == null)
            return true;
        LocalDate endDate = endDateTime.toLocalDate();
        boolean isValid = endDate.isAfter(LocalDate.now());

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MissionErrorCode.INVALID_DURATION.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}