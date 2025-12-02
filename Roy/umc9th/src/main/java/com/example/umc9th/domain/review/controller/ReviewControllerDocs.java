package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;

public interface ReviewControllerDocs {
    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API By Roy",
            description = "내가 작성한 리뷰를 조회합니다. 페이지네이션으로 제공합니다."
    )

    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    public ApiResponse<ReviewResDTO.previewListDTO> getMyReviews(@Valid @ParameterObject ReviewReqDTO.previewListDTO dto, Pageable pageable);
}
