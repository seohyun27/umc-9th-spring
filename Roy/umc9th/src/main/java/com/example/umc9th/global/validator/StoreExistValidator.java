package com.example.umc9th.global.validator;

import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.annotation.ExistStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {


    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;
        boolean isValid = storeRepository.existsById(value);

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StoreErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}