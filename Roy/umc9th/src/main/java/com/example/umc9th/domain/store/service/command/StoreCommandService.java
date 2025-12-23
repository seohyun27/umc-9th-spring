package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.registerDTO register(StoreReqDTO.registerDTO dto);
}
