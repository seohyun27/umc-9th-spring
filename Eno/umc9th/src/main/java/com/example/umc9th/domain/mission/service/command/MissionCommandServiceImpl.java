package com.example.umc9th.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    @Override
    public void startMission(Long missionId, Long memberId) {
        // Id로 미션 존재여부 확인
        // 미션Id와 멤버 Id로 멤버 미션 생성

        // 오류 발생 시 에러 핸들러로 넘겨줄 것
    }
}
