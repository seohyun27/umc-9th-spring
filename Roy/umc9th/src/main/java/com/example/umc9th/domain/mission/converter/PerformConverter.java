package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.dto.PerformResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.Perform;
import com.example.umc9th.domain.store.entity.Store;

public class PerformConverter {
    //Entity -> DTO
    public static PerformResDTO.registerDTO toRegisterDTO(Perform perform)
    {
        return PerformResDTO.registerDTO.builder()
                .performId(perform.getId())
                .creatAt(perform.getCreated_at())
                .build();
    }

    //DTO->Entity
    public static Perform toPerform(PerformReqDTO.registerDTO dto, Member member, Mission mission)
    {
        return Perform.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}
