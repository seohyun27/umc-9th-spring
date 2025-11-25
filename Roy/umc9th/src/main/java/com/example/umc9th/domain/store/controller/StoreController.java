package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreCommandService storeCommandService;

    public StoreController(StoreCommandService storeCommandService) {
        this.storeCommandService = storeCommandService;
    }

    @PostMapping("/add")
    public ApiResponse<StoreResDTO.registerDTO> registerStore(@RequestBody @Valid StoreReqDTO.registerDTO dto)
    {
        return ApiResponse.onSuccess(StoreSuccessCode.CREATED, storeCommandService.register(dto));
    }
}
