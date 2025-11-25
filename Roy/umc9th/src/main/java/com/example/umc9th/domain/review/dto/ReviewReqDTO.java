package com.example.umc9th.domain.review.dto;

import com.example.umc9th.global.annotation.ExistMember;
import com.example.umc9th.global.annotation.ExistStore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.*;

public class ReviewReqDTO {
    public record registerDTO(
            @Max(5)
            Integer rate,
            @Size(max = 200)
            String content,
            @NotNull @ExistStore
            Long storeId,
            @NotNull @ExistMember
            Long memberId,
            @Size(max = 3)
            List<String> reviewPhotos
    ){}
}
