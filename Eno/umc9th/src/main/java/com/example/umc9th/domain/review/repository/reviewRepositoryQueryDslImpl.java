package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.QMember;
import com.example.umc9th.domain.review.entity.QShopReview;
import com.example.umc9th.domain.review.entity.ShopReview;
import com.example.umc9th.domain.shop.entity.QShop;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository     // QueryDSL의 구현체인데 왜 워크북에는 서비스 클래스로 되어 있지?
@RequiredArgsConstructor
public class reviewRepositoryQueryDslImpl implements reviewRepositoryQueryDsl {

    private final EntityManager em;

    @Override
    public List<ShopReview> searchShopReview(Predicate predicate){
        // 인자의 Predicate의 경우 queryDSL에서 제공하는 것인지 아닌지 잘 보고 import

        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스 선언
        QShopReview review = QShopReview.shopReview;
        QShop shop = QShop.shop;
        QMember member = QMember.member;

        return queryFactory
                .selectFrom(review)
                .join(review.shop, shop)
                .join(review.member, member)
                .where(predicate)
                .fetch();
    }

    // 현재 지역명과 별점은 ShopReview 엔티티 안에 존재 → Q클래스를 통한 join이 필요하지 않다.
    // 모든 검색의 조건은 Service에서 전달받은 Predicate를 통해 결정된다.
    // 어떤 member의 리뷰인지, 어떤 가게의 리뷰인지, 어떤 별점을 가진 리뷰인지는 서비스에서 결정되는 것.
}
