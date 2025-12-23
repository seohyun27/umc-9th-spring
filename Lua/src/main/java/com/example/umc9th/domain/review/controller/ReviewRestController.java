package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ApiResponse<Page<ReviewResponseDTO.MyReviewDTO>> getMyReviews(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "shopId", required = false) Long shopId,
            @RequestParam(name = "rating", required = false) Integer rating,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        Page<ReviewResponseDTO.MyReviewDTO> result = reviewService.getMyReviewList(userId, shopId, rating, page);
        return ApiResponse.onSuccess(result);
    }
}