package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.global.annotation.ExistMember;
import com.example.umc9th.global.annotation.ExistMission;
import com.example.umc9th.global.annotation.NotExistPerform;
import org.springframework.validation.annotation.Validated;

public class PerformReqDTO {
    @NotExistPerform
    public record registerDTO(
            @ExistMission
            Long missionId,
            @ExistMember
            Long memberId
    ){}
}
