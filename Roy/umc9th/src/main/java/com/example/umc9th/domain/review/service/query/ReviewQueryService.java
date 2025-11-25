package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.repository.ReviewQueryDslImpl;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    public List<ReviewResDTO.ReviewItemDTO> searchReview(String query, String type) {
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
            String secondQuery = querySplit[1];

            builder.and(review.store.name.contains(firstQuery));
            builder.and(review.rate.eq(Integer.parseInt(secondQuery)));
        }
        return reviewRepository.searchReview(builder);
    }
}