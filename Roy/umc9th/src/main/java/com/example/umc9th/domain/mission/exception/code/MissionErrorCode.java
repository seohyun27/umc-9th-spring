package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    INVALID_DURATION(HttpStatus.BAD_REQUEST,"MISSION400_1","미션 기간은 최소 1일 이상이여야 합니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
