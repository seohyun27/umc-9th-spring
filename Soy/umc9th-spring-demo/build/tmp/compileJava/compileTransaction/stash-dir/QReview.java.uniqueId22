package com.example.umc9thspringdemo.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 452079052L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.example.umc9thspringdemo.global.entity.QBaseEntity _super = new com.example.umc9thspringdemo.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.umc9thspringdemo.domain.member.entity.QMember member;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final com.example.umc9thspringdemo.domain.store.entity.QRestaurant restaurant;

    public final ListPath<com.example.umc9thspringdemo.domain.review.entity.mapping.ReviewComment, com.example.umc9thspringdemo.domain.review.entity.mapping.QReviewComment> ReviewCommentList = this.<com.example.umc9thspringdemo.domain.review.entity.mapping.ReviewComment, com.example.umc9thspringdemo.domain.review.entity.mapping.QReviewComment>createList("ReviewCommentList", com.example.umc9thspringdemo.domain.review.entity.mapping.ReviewComment.class, com.example.umc9thspringdemo.domain.review.entity.mapping.QReviewComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.umc9thspringdemo.domain.member.entity.QMember(forProperty("member")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new com.example.umc9thspringdemo.domain.store.entity.QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

