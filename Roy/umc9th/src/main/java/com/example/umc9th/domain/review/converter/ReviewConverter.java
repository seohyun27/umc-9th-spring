package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ReviewConverter {

    //Entity->DTO
    public static ReviewResDTO.registerDTO toRegisterDTO(Review review)
    {
        return ReviewResDTO.registerDTO.builder()
                .reviewId(review.getId())
                .createAt(review.getCreated_at())
                .build();
    }

    //DTO->Entity
    public static Review toReview(ReviewReqDTO.registerDTO dto, Member member, List<ReviewPhoto> photos, Store store)
    {
        return Review.builder()
                .rate(dto.rate())
                .member(member)
                .content(dto.content())
                .store(store)
                .member(member)
                .reviewPhotos(new ArrayList<>())
                .build();
    }

    //Entity -> DTO
    public static ReviewResDTO.previewListDTO toPreviewListDTO(Page<Review> result)
    {
        return ReviewResDTO.previewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewItemDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
    public static ReviewResDTO.ReviewItemDTO toReviewItemDTO(Review review)
    {
        return ReviewResDTO.ReviewItemDTO.builder()
                .id(review.getId())
                .writer(review.getMember().getName())
                .createdAt(review.getCreated_at())
                .content(review.getContent())
                .rate(review.getRate())
                .storeName(review.getStore().getName())
                .build();
    }
}
