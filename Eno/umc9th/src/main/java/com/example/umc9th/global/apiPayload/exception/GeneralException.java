package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    // 해당 글로벌 예외를 상속받아 각 도메인의 에러를 생성하면 됨!!

    private final BaseErrorCode code;
}
