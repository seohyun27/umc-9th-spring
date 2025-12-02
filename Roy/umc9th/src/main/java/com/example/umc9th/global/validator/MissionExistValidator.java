package com.example.umc9th.global.validator;

import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.global.annotation.ExistMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {


    private final MissionRepository missionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;
        boolean isValid = missionRepository.existsById(value);

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MissionErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}