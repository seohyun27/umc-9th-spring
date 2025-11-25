package com.example.umc9th.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record registerDTO(
            Long storeId
    ){}
}
