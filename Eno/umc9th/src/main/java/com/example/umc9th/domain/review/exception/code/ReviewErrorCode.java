package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    // 예시 오류
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당하는 가게가 존재하지 않습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "REVIEW400_2", "이미 해당 가게에 리뷰를 작성하였습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
