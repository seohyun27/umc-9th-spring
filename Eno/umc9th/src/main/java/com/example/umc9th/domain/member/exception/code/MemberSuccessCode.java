package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 사용자를 조회했습니다."),

    // 회원가입 성공 시 201번 생성 코드로 돌려줌
    CREATED(HttpStatus.CREATED,
            "MEMBER201_1",
                    "성공적으로 회원가입이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
