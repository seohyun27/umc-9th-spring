package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.dto.PerformResDTO;
import com.example.umc9th.domain.mission.exception.code.PerformSuccessCode;
import com.example.umc9th.domain.mission.service.command.PerformService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users/{memberId}/mission")
public class PerformController {
    private final PerformService performService;

    @PostMapping("/{missionId}/add")
    public ApiResponse<PerformResDTO.registerDTO> addPerform(
            @Valid @ParameterObject PerformReqDTO.registerDTO dto
    )
    {
        return ApiResponse.onSuccess(PerformSuccessCode.CREATED,performService.register(dto));
    }
}
