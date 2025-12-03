package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public class MissionConverter {
    //Entity -> DTO
    public static MissionResDTO.registerDTO toRegisterDTO(Mission mission)
    {
        return MissionResDTO.registerDTO.builder()
                .missionId(mission.getId())
                .createAt(mission.getCreated_at())
                .build();
    }

    //DTO->Entity
    public static Mission toMission(MissionReqDTO.registerDTO dto, Store store)
    {
        return Mission.builder()
                .point(dto.point())
                .endDate(dto.endDate())
                .standard_amount(dto.standardAmount())
                .store(store)
                .build();
    }
    public static MissionResDTO.MissionItemDTO toMissionItem(Mission mission)
    {
        return MissionResDTO.MissionItemDTO.builder()
                .storeName(mission.getStore().getName())
                .missionId(mission.getId())
                .point(mission.getPoint())
                .standardAmount(mission.getStandard_amount())
                .endDate(mission.getEndDate())
                .build();
    }
    //Page -> DTO
    public static MissionResDTO.previewListDTO toPreviewList(Page<Mission> result)
    {
        return MissionResDTO.previewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionItem)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
}
