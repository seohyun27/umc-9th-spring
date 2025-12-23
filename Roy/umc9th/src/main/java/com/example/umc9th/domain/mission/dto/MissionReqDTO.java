package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.global.annotation.ExistMember;
import com.example.umc9th.global.annotation.ExistStore;
import com.example.umc9th.global.annotation.ManagerPermission;
import com.example.umc9th.global.annotation.ValidDuration;
import com.example.umc9th.global.validator.ManagerPermissionValidator;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MissionReqDTO {
    public record registerDTO (
            Long point,
            Long standardAmount,
            @NotNull @ValidDuration
            LocalDateTime endDate,
            @NotNull @ExistStore
            Long storeId,
            @NotNull
            @ExistMember
            @ManagerPermission
            Long managerId
    ){
        @GroupSequence({
                registerDTO.class,
                ExistMember.class,
                ManagerPermissionValidator.class
        })
        public interface ValidationOrder {}
    }
    public record previewListDTO(
            @ExistStore Long storeId
    ){}
}
