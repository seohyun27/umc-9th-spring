package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;

public interface MissionControllerDocs {
    // 가게의 미션 목록 조회
    @Operation(
            summary = "가게의 미션 목록 조회 API By Roy",
            description = "가게의 미션 목록을 조회합니다. 페이지네이션으로 제공합니다."
    )

    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    public ApiResponse<MissionResDTO.previewListDTO> getMissionsByStore(@Valid @ParameterObject MissionReqDTO.previewListDTO dto, Pageable pageable);
}
