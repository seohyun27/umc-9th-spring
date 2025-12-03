package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;

public class PerformResDTO {
    @Builder
    public record registerDTO(
            Long performId,
            LocalDateTime creatAt
    ){}
    @Builder
    public record completedDTO(
            MissionResDTO.MissionItemDTO mission,
            MissionStatus status,
            LocalDateTime finishedAt
    ){}
}
