package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreViewListDTO findReview(
            Long shopId,
            Pageable pageable
    );

    ReviewResDTO.ReviewPreViewListDTO findMyReview(
            Long memberId,
            Pageable pageable
    );
}
