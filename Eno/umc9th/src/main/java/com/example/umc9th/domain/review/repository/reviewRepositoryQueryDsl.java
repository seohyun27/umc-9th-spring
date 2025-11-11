package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.ShopReview;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface reviewRepositoryQueryDsl {

    // 구현하고 싶은 메소드들을 인터페이스 안에서 미리 정의
    // 인터페이스에 정의하는 모든 메서드는 자동으로 public이고 abstract가 됨
    // 키워드 작성이 필요가 없다

    List<ShopReview> searchShopReview(Predicate predicate);

}
