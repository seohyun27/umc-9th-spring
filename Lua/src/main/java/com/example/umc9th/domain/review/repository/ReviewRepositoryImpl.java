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