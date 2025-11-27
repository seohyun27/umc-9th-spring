package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionCommandService {
    MemberMissionResDTO.MissionFinishDTO completeMission(Long missionId, Long memberId);

}
