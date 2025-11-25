package com.example.umc9th.global.validator;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.MemberType;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.annotation.ExistMember;
import com.example.umc9th.global.annotation.ManagerPermission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistMember, Long> {

    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;
        boolean isValid = memberRepository.existsById(value);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MemberErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }
        return isValid;
    }
}