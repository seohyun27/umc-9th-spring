package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record registerDTO(
            Long missionId,
            LocalDateTime createAt
    ){}
    @Builder
    public record MissionItemDTO(
            Long missionId,
            LocalDateTime endDate,
            Long point,
            Long standardAmount,
            String storeName

    ){}
    @Builder
    public record previewListDTO(
            List<MissionResDTO.MissionItemDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
}
