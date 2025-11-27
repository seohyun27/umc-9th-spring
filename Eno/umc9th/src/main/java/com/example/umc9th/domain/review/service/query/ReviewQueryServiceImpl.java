package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.shop.entity.Shop;
import com.example.umc9th.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    final ShopRepository shopRepository;
    final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            Long shopId,
            Pageable pageable
    ){
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.SHOP_NOT_FOUND));

        Page<Review> result = reviewRepository.findAllByShopId(shopId, pageable);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
