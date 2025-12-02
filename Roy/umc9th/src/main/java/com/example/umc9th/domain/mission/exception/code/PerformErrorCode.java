package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PerformErrorCode implements BaseErrorCode {
    ALREADY_EXISTS(HttpStatus.CONFLICT,"PERFORM409_1","이미 추가한 미션입니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
