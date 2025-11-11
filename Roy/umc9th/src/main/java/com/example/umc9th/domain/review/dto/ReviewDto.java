package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.umc9th.domain.review.entity.Review}
 */
@Getter
@AllArgsConstructor
public class ReviewDto {
    Long id;
    int rate;
    String content;
    String storeName;
}