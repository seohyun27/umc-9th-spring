package com.example.umc9th.global.validator;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.MemberType;
import com.example.umc9th.domain.member.exception.FoodException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.annotation.ManagerPermission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagerPermissionValidator implements ConstraintValidator<ManagerPermission, Long> {

    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Member member = memberRepository.findById(value)
                .orElseThrow(()->new FoodException(MemberErrorCode.NOT_FOUND));;
        boolean isValid = member.getType() == MemberType.Manager;

        if (!isValid) {
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MemberErrorCode.FORBIDDEN.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}