package com.example.umc9th.domain.store.dto;

import com.example.umc9th.global.annotation.ExistCategory;
import com.example.umc9th.global.annotation.ExistMember;
import com.example.umc9th.global.annotation.ExistRegion;
import com.example.umc9th.global.annotation.ManagerPermission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public class StoreReqDTO {
    public record registerDTO(
            @NotBlank @Size(max = 50)
            String address,
            @NotBlank @Size(max = 10)
            String name,
            LocalTime openTime,
            LocalTime closeTime,
            @NotNull @ExistCategory
            Long categoryId,
            @NotNull @ExistMember @ManagerPermission
            Long memberId,
            @NotNull @ExistRegion
            Long regionId
    ){}
}
