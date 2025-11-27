package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController implements MissionControllerDocs {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 미션 도전하기
    @PostMapping("/missions/{missionId}/member-missions")
    @Override
    public ApiResponse<Void> startMission(
            @PathVariable Long missionId,
            Long memberId){
        // 로그인 관련 로직이 없으므로 memberId를 인자값으로 입력받는 형태로 임시 구현하였다

        missionCommandService.startMission(missionId, memberId);
        return ApiResponse.onSuccess(MemberMissionSuccessCode.CREATED, null);
    }

    // 특정 가게의 미션 목록 조회
    @GetMapping("/shops/{shopId}/missions")
    @Override
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getShopMission(
            @PathVariable Long shopId,
            @PageableDefault(page = 0, size = 10, sort = "finishDate", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionQueryService.findShopMission(shopId, pageable));
    }
}
