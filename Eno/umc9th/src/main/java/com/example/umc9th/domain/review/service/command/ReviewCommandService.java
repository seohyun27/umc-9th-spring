package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;

public interface ReviewCommandService {
    void addReview(ReviewReqDTO.AddDTO dto, Long memberId);
}
