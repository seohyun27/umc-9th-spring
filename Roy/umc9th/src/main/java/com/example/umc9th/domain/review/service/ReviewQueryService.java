package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewQueryDslImpl;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryService {
    private final ReviewQueryDslImpl reviewRepository;
    public ReviewQueryService(ReviewQueryDslImpl reviewRepository)
    {
        this.reviewRepository = reviewRepository;
    }
    public List<ReviewDto> searchReview(String query, String type) {
        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("store")) {
            builder.and(review.store.name.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.rate.eq(Integer.parseInt(query)));
        }
        System.out.println(type+query);
        if (type.equals("both")) {
            String[] querySplit = query.split("&");
            String firstQuery = querySplit[0];
            String secodeQuery = querySplit[1];

            builder.and(review.store.name.contains(firstQuery));
            builder.and(review.rate.eq(Integer.parseInt(secodeQuery)));
        }
        return reviewRepository.searchReview(builder);
    }
}