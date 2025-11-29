package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PerformSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED,"PERFORM200_1","미션을 나의 미션에 추가하였습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
