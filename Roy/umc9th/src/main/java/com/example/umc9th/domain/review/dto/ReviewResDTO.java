package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewItemDTO {
        Long id;
        String writer;
        int rate;
        String content;
        String storeName;
        LocalDateTime createdAt;
    }
    @Builder
    public record registerDTO(Long reviewId, LocalDateTime createAt){}
    @Builder
    public record previewListDTO(
            List<ReviewItemDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
}