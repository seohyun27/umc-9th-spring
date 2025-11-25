package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;
    @GetMapping("/search")
    public ApiResponse<List<ReviewResDTO.ReviewItemDTO>> searchReview(@RequestParam String query, @RequestParam String type){
        List<ReviewResDTO.ReviewItemDTO> reviewList = reviewQueryService.searchReview(query,type);
        GeneralSuccessCode code = GeneralSuccessCode.SUCCESS_CODE;
        return ApiResponse.onSuccess(code,reviewList);
    }
    @PostMapping("/add")
    public ApiResponse<ReviewResDTO.registerDTO> register(@RequestBody @Valid ReviewReqDTO.registerDTO dto)
    {
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED,reviewCommandService.register(dto));
    }
}
