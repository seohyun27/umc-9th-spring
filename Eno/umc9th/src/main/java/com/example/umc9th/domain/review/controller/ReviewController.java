package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shops")
public class ReviewController implements ReviewControllerDocs {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 가게에 리뷰 작성
    @PostMapping("/{shopId}/reviews")
    @Override
    public ApiResponse<Void> addReview(@RequestBody ReviewReqDTO.AddDTO dto, Long memberId){
        // 로그인 관련 로직이 없으므로 memberId를 인자값으로 입력받는 형태로 임시 구현하였다

        reviewCommandService.addReview(dto, memberId);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, null);
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/{shopId}/reviews")
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @PathVariable Long shopId,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(shopId, pageable));
    }
}
