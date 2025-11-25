package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionCommandService missionCommandService;

    @PostMapping("{missionId}/member-missions")
    public ApiResponse<Void> startMission(
            @PathVariable Long missionId,
            Long memberId){
        // 로그인 관련 로직이 없으므로 memberId를 인자값으로 입력받는 형태로 임시 구현하였다

        missionCommandService.startMission(missionId, memberId);
        return ApiResponse.onSuccess(MemberMissionSuccessCode.CREATED, null);
    }
}
