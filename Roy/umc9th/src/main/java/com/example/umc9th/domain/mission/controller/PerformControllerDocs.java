package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.dto.PerformResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface PerformControllerDocs {
    // 나의 미션 목록 조회
    @Operation(
            summary = "나의 미션 조회 API By Roy",
            description = "나의 미션을 조회합니다. 진행중인 미션 : IN_PROGRESS ,  완료 : COMPLETED, 없으면 모두 가져옵니다."
    )

    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.previewListDTO> getMissionsByStore(@Valid @ParameterObject PerformReqDTO.previewListDTO dto, @PageableDefault(size = 10) Pageable pageable);
    // 미션 완료하기
    @Operation(
            summary = "미션 완료 API By Roy",
            description = "나의 미션을 완료합니다."
    )

    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "없는 미션"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "이미 완료한 미션"),
    })
    ApiResponse<PerformResDTO.completedDTO> setCompletedMission(@Valid @ParameterObject PerformReqDTO.completedDTO dto);
}
