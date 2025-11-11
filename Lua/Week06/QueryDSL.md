### ReviewRepository.java

```java
//기존 ReviewRepository에서 ReviewRepositoryCustom을 상속받도록함.

package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    @Query("""
           SELECT r
           FROM Review r
           JOIN FETCH r.user u
           ORDER BY r.createdAt DESC
           """)
    List<Review> findAllWithUserOrderByCreatedAtDesc();
}
```
#### rating에서 null 값을 받기 위해서 Integer 사용.
### ReviewRepositoryCustom.java

```java
package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    Page<Review> findMyReviewsByFilters(Long userId, String shopName, Integer rating, Pageable pageable);
}
```

### ReviewRepositoryCustomImpl.java

```java
package com.example.demo.repository;

import com.example.demo.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.entity.QReview.review;
import static com.example.demo.entity.QShop.shop;
import static com.example.demo.entity.QUser.user;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviewsByFilters(Long userId, String shopName, Integer rating, Pageable pageable) {
        //리뷰 목록 조회 쿼리
        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.shop, shop).fetchJoin()
                .join(review.user, user)
                .where(
                        user.id.eq(userId),
                        shopNameEq(shopName),
                        ratingEq(rating)
                )
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        //전체 리뷰 조회 쿼리
        Long totalCount = queryFactory
                .select(review.count())
                .from(review)
                .join(review.shop, shop)
                .join(review.user, user)
                .where(
                        user.id.eq(userId),
                        shopNameEq(shopName),
                        ratingEq(rating)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, totalCount != null ? totalCount : 0L);
    }
    //shopName 동적쿼리
    private BooleanExpression shopNameEq(String shopName) {
        return hasText(shopName) ? shop.name.eq(shopName) : null;
    }
    //ratingEq 동적 쿼리
    private BooleanExpression ratingEq(Integer rating) {
        return (rating != null && rating > 0) ? review.rating.eq(rating) : null;
    }
}
```
