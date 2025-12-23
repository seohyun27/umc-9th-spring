package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED,
            "MEMBER_MISSION201_1",
            "미션 도전이 성공적으로 이루어졌습니다."),

    MISSION_COMPLETED(HttpStatus.OK,
            "MEMBER_MISSION200_1",
            "미션이 완료되었습니다.")
            ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
