package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDTO.MissionFinishDTO completeMission(Long missionId, Long memberId) {

        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionException(MemberMissionErrorCode.NOT_FOUND));

        memberMission.completed();

        return MemberMissionConverter.toMemberMissionFinishDTO(memberMission);

    }
}
