package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.dto.PerformResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.Perform;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

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

    public static MissionResDTO.previewListDTO toPreviewList(Page<Perform> result)
    {
        return MissionResDTO.previewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(Perform::getMission)
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
    public static PerformResDTO.completedDTO toCompleted(Perform perform)
    {
        return PerformResDTO.completedDTO.builder()
                .mission(MissionConverter.toMissionItem(perform.getMission()))
                .finishedAt(perform.getFinished_at())
                .status(perform.getStatus())
                .build();
    }
}
