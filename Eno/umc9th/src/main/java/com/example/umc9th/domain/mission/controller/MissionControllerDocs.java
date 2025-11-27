package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface MissionControllerDocs {

    // 미션 도전하기
    @Operation(
            summary = "미션 도전하기 API By 이노 (개발 중)",
            description = "새로운 미션에 도전합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("{missionId}/member-missions")
    ApiResponse<Void> startMission(
            @PathVariable Long missionId,
            Long memberId);

    // 특정 가게의 미션 목록 조회
    @Operation(
            summary = "특정 가게의 미션 조회 API By 이노 (개발 중)",
            description = "특정 가게의 모든 미션을 불러옵니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/shops/{shopId}/missions")
    ApiResponse<MissionResDTO.MissionPreViewListDTO> getShopMission(
            @PathVariable Long shopId,
            @PageableDefault(page = 0, size = 10, sort = "finishDate", direction = Sort.Direction.DESC) Pageable pageable
    );
}
