package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.PerformConverter;
import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.dto.PerformResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.Perform;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.PerformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformCommandServiceImpl implements PerformCommandService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final PerformRepository performRepository;

    @Override
    public PerformResDTO.registerDTO register(PerformReqDTO.registerDTO dto)
    {
        Member member = memberRepository.findById(dto.memberId()).orElse(null);
        Mission mission = missionRepository.findById(dto.missionId()).orElse(null);
        Perform perform = PerformConverter.toPerform(dto,member,mission);
        performRepository.save(perform);
        return PerformConverter.toRegisterDTO(perform);
    }
}
