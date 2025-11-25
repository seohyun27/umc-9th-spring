package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//qdsl
@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final EntityManager em;


    @Override
    public List<ReviewResDTO.ReviewItemDTO> searchReview(
            Predicate predicate
    ){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QStore store = QStore.store;

        return queryFactory
                .select(com.querydsl.core.types.Projections.constructor(
                        ReviewResDTO.ReviewItemDTO.class,
                        review.id,
                        review.rate,
                        review.content,
                        store.name
                ))
                .from(review)
                .innerJoin(review.store,store)
                .where(predicate)
                .fetch();
    }
}
