package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);    // 생성자에서 전역 예외 처리로 code를 전달
    }
}
