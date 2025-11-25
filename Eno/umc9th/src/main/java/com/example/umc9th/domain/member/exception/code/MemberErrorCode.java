package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // 회원가입 실패는 여러 경우로 발생할 수 있음
    DUPLICATE_MEMBER(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "이미 존재하는 회원입니다."),

    INVALID_SIGNUP_REQUEST(HttpStatus.BAD_REQUEST,
            "MEMBER400_2",
            "회원가입 요청 값이 올바르지 않습니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
