package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {
    @Builder
    public record registerDTO(
            Long missionId,
            LocalDateTime createAt
    ){}
}
