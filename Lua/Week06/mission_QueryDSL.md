### ReviewRepositoryImpl.java

```java
package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.shop.entity.QShop;
import com.example.umc9th.domain.user.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviews(User user, Long shopId, Integer rating, Pageable pageable) {

        QReview review = QReview.review;
        QShop shop = QShop.shop;

        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.shop, shop).fetchJoin()
                .where(
                        review.user.eq(user),
                        eqShopId(shopId),
                        eqRating(rating)
                )
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        review.user.eq(user),
                        eqShopId(shopId),
                        eqRating(rating)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqShopId(Long shopId) {
        return shopId != null ? QReview.review.shop.id.eq(shopId) : null;
    }

    private BooleanExpression eqRating(Integer rating) {
        if (rating == null) {
            return null;
        }
        return QReview.review.rating.goe(rating)
                .and(QReview.review.rating.lt(rating + 1));
    }
}
```
----------------
### ReviewRestController.java

```java
package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public Page<ReviewResponseDTO.MyReviewDTO> getMyReviews(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "shopId", required = false) Long shopId,
            @RequestParam(name = "rating", required = false) Integer rating,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        return reviewService.getMyReviewList(userId, shopId, rating, page);
    }
}
```
-----------------
### ReviewService.java

```java
package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public Page<ReviewResponseDTO.MyReviewDTO> getMyReviewList(Long userId, Long shopId, Integer rating, int page) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Page<Review> reviewPage = reviewRepository.findMyReviews(user, shopId, rating, PageRequest.of(page, 10));

        return reviewPage.map(review -> ReviewResponseDTO.MyReviewDTO.builder()
                .shopName(review.getShop().getName())
                .nickname(user.getNickname())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build());
    }
}
```
-------
### ReviewResponseDTO.java
```java
package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewDTO {
        private String shopName;
        private String nickname;
        private Double rating;
        private String reviewText;
        private LocalDate createdAt;
    }
}
```
