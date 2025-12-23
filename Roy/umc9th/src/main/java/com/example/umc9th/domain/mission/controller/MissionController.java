package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/add")
    public ApiResponse<MissionResDTO.registerDTO> register(
            @RequestBody @Valid
            MissionReqDTO.registerDTO dto)
    {
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, missionCommandService.register(dto));
    }
    @Override
    @GetMapping("/{storeId}")
    public ApiResponse<MissionResDTO.previewListDTO> getMissionsByStore(@Valid @ParameterObject MissionReqDTO.previewListDTO dto, @PageableDefault(size = 10) Pageable pageable)
    {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionQueryService.findMissionsByStore(dto,pageable));
    }
}
