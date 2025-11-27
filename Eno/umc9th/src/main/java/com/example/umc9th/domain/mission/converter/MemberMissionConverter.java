package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;

public class MemberMissionConverter {
    public static MemberMissionResDTO.MissionFinishDTO toMemberMissionFinishDTO(
            MemberMission memberMission
    ) {
        return MemberMissionResDTO.MissionFinishDTO.builder()
                .memberMissionId(memberMission.getId())
                .isCompleted(memberMission.isCompleted()) // true
                .missionBody(memberMission.getMission().getText())
                .earnedPoint(memberMission.getMission().getPoint())
                .shopName(memberMission.getMission().getShop().getName()) // Shop 엔티티에 getName()이 있다고 가정
                .completedAt(memberMission.getFinishAt()) // 완료 시점 (Service에서 세팅해줘야 함)
                .build();
    }
}
