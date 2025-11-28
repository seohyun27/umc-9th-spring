package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;

    @Override
    public void startMission(Long missionId, Long memberId) {
        // Id로 미션 존재여부 확인
        // 미션Id와 멤버 Id로 멤버 미션 생성

        // 오류 발생 시 에러 핸들러로 넘겨줄 것
    }
}
