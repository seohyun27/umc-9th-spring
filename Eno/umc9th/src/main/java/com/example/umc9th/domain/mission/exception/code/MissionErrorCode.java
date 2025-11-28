package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    SHOP_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당하는 가게가 존재하지 않습니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "해당하는 미션이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
