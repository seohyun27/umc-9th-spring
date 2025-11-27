package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record MissionPreViewListDTO(
            List<MissionResDTO.MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MissionPreViewDTO(
            Long id,
            char dtype,
            int point,
            String body,
            LocalDate finishDate
    ){}
}

