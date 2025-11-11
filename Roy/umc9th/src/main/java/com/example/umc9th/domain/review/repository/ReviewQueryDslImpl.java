package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

//qdsl
@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final EntityManager em;


    @Override
    public List<ReviewDto> searchReview(
            Predicate predicate
    ){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QStore store = QStore.store;

        return queryFactory
                .select(com.querydsl.core.types.Projections.constructor(
                        ReviewDto.class,
                        review.id,
                        review.rate,
                        review.content,
                        store.name
                ))
                .from(review)
                .leftJoin(review.store,store)
                .where(predicate)
                .fetch();
    }
}
