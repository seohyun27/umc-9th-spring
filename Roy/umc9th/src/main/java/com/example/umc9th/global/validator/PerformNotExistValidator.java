package com.example.umc9th.global.validator;

import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.exception.code.PerformErrorCode;
import com.example.umc9th.domain.mission.repository.PerformRepository;
import com.example.umc9th.global.annotation.NotExistPerform;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PerformNotExistValidator implements ConstraintValidator<NotExistPerform,PerformReqDTO.registerDTO> {


    private final PerformRepository performRepository;

    @Override
    public boolean isValid(PerformReqDTO.registerDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true;
        boolean isValid = !(performRepository.existsByMemberIdAndMissionId(dto.memberId(),dto.missionId()));

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(PerformErrorCode.ALREADY_EXISTS.getMessage()).addConstraintViolation();
        }
        return isValid;
    }
}