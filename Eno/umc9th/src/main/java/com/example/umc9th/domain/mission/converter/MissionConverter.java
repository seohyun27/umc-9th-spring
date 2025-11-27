package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MissionConverter {
    // result -> DTO
    public static MissionResDTO.MissionPreViewListDTO toMissionPreviewListDTO(
            Page<Mission> result
    ){
        return MissionResDTO.MissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionPreViewDTO toMissionPreviewDTO(
            Mission mission
    ){
        return MissionResDTO.MissionPreViewDTO.builder()
                .id(mission.getId())
                .dtype(mission.getDtype())
                .point(mission.getPoint())
                .body(mission.getText())
                .finishDate(LocalDate.from(mission.getFinishDate()))
                .build();
    }
}
