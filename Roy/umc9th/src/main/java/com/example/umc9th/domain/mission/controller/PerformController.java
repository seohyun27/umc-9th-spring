package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.dto.PerformResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.exception.code.PerformSuccessCode;
import com.example.umc9th.domain.mission.service.command.PerformCommandService;
import com.example.umc9th.domain.mission.service.query.PerformQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users/{memberId}/missions")
public class PerformController implements PerformControllerDocs {
    private final PerformCommandService performCommandService;
    private final PerformQueryService performQueryService;

    @PostMapping("/{missionId}/add")
    public ApiResponse<PerformResDTO.registerDTO> addPerform(
            @Valid @ParameterObject PerformReqDTO.registerDTO dto
    )
    {
        return ApiResponse.onSuccess(PerformSuccessCode.CREATED, performCommandService.register(dto));
    }
    @Override
    @GetMapping()
    public ApiResponse<MissionResDTO.previewListDTO> getMissionsByStore(@Valid @ParameterObject PerformReqDTO.previewListDTO dto, @PageableDefault(size = 10) Pageable pageable)
    {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, performQueryService.findMyMissions(dto,pageable));
    }
    @PatchMapping("/{missionId}/completed")
    public ApiResponse<PerformResDTO.completedDTO> setCompletedMission(@Valid @ParameterObject PerformReqDTO.completedDTO dto)
    {
        return ApiResponse.onSuccess(PerformSuccessCode.COMPLETED,performQueryService.completeMyMission(dto));
    }
}
