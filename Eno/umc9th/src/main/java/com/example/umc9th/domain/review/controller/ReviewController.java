package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.member.service.command.MemberCommandServiceImpl;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandServiceImpl;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewCommandServiceImpl reviewCommandService;

    @PostMapping("/bakerys/{bakeryId}/reviews")
    public ApiResponse<Void> addReview(@RequestBody ReviewReqDTO.AddDTO dto, Long memberId){
        // 로그인 관련 로직이 없으므로 memberId를 인자값으로 입력받는 형태로 임시 구현하였다

        reviewCommandService.addReview(dto, memberId);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, null);
    }
}
