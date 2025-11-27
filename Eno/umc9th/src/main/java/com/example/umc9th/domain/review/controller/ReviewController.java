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
@RequestMapping("/api")
public class ReviewController implements ReviewControllerDocs {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 가게에 리뷰 작성
    @PostMapping("/shops/{shopId}/reviews")
    @Override
    public ApiResponse<Void> addReview(@RequestBody ReviewReqDTO.AddDTO dto, Long memberId){
        // 로그인 관련 로직이 없으므로 memberId를 인자값으로 입력받는 형태로 임시 구현하였다

        reviewCommandService.addReview(dto, memberId);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, null);
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/shops/{shopId}/reviews")
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @PathVariable Long shopId,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            // @PageableDefault : pageable 객체가 없다면 기본값을 사용할 것
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(shopId, pageable));
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/members/{memberId}/reviews")
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        /**
         * api 경로를 /members/me/reviews로 한 뒤 로그인 정보를 받아 처리하는 것이 더 올바른 설계이다
         * 단, 현재 로그인 기능이 존해하지 않으므로 경로 변수에서 id를 받아오는 것으로 임시 처리한다
         */

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReview(memberId, pageable));
    }
}
