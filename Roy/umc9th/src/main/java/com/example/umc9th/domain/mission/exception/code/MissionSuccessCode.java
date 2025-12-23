package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED,"MISSION201_1","미션을 등록하였습니다."),
    FOUND(HttpStatus.FOUND,"MISSION302_1","미션을 조회하였습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
