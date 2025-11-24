package com.example.umc9th.global.validator;

import com.example.umc9th.domain.store.exception.code.RegionErrorCode;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.global.annotation.ExistRegion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = regionRepository.existsById(value);

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RegionErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}