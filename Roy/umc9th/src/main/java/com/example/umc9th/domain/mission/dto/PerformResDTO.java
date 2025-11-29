package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class PerformResDTO {
    @Builder
    public record registerDTO(
            Long performId,
            LocalDateTime creatAt
    ){}
}
