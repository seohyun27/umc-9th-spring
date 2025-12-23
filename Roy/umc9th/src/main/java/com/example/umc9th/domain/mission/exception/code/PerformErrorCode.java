package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PerformErrorCode implements BaseErrorCode {
    ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"PERFORM400_1","이미 추가한 미션입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,"PERFORM404_1","나의 미션에 추가되지 않았습니다."),
    ALREADY_COMPLETED(HttpStatus.CONFLICT,"PERFORM400_2","이미 완료한 미션입니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
