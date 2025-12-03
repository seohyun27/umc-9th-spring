package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.global.annotation.ExistMember;
import com.example.umc9th.global.annotation.ExistMission;
import com.example.umc9th.global.annotation.ExistStore;
import com.example.umc9th.global.annotation.NotExistPerform;

public class PerformReqDTO {
    @NotExistPerform
    public record registerDTO(
            @ExistMission
            Long missionId,
            @ExistMember
            Long memberId
    ){}
    public record previewListDTO(
            @ExistMember Long memberId,
            MissionStatus status
    ){}
}
