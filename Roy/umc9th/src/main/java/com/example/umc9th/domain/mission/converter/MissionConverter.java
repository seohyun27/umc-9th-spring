package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;

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
}
