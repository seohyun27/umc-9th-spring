package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewItemDTO {
        Long id;
        int rate;
        String content;
        String storeName;
    }
    @Builder
    public record registerDTO(Long reviewId, LocalDateTime createAt){}
}