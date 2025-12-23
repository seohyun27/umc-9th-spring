package com.example.umc9th.domain.review.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {

    // 생성자 : 에러 코드를 받아 부모(GeneralException)에게 넘겨주도록 작성
    public ReviewException(BaseErrorCode code) {
        super(code);
    }

    // 각 도메인 예외는 전역 예외 클래스를 상속받는 구조를 사용한다.
    // 전역 예외 처리기(@RestControllerAdvice)에서 부모인 전역 예외만을 잡으면 된다.
}
