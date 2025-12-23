package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {
    List<ReviewResDTO.ReviewItemDTO> searchReview(Predicate predicate);
}
