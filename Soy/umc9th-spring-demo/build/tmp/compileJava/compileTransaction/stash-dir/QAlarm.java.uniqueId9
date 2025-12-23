package com.example.umc9thspringdemo.domain.alarm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarm is a Querydsl query type for Alarm
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarm extends EntityPathBase<Alarm> {

    private static final long serialVersionUID = -1679394142L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarm alarm = new QAlarm("alarm");

    public final com.example.umc9thspringdemo.global.entity.QBaseEntity _super = new com.example.umc9thspringdemo.global.entity.QBaseEntity(this);

    public final EnumPath<com.example.umc9thspringdemo.domain.alarm.enums.AlarmType> alarmType = createEnum("alarmType", com.example.umc9thspringdemo.domain.alarm.enums.AlarmType.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final com.example.umc9thspringdemo.domain.inquiry.entity.QInquiry inquiry;

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isRead = createBoolean("isRead");

    public final com.example.umc9thspringdemo.domain.member.entity.QMember member;

    public final com.example.umc9thspringdemo.domain.mission.entity.QMission mission;

    public final com.example.umc9thspringdemo.domain.store.entity.QRestaurant restaurant;

    public final com.example.umc9thspringdemo.domain.review.entity.mapping.QReviewComment reviewComment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAlarm(String variable) {
        this(Alarm.class, forVariable(variable), INITS);
    }

    public QAlarm(Path<? extends Alarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarm(PathMetadata metadata, PathInits inits) {
        this(Alarm.class, metadata, inits);
    }

    public QAlarm(Class<? extends Alarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquiry = inits.isInitialized("inquiry") ? new com.example.umc9thspringdemo.domain.inquiry.entity.QInquiry(forProperty("inquiry"), inits.get("inquiry")) : null;
        this.member = inits.isInitialized("member") ? new com.example.umc9thspringdemo.domain.member.entity.QMember(forProperty("member")) : null;
        this.mission = inits.isInitialized("mission") ? new com.example.umc9thspringdemo.domain.mission.entity.QMission(forProperty("mission"), inits.get("mission")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new com.example.umc9thspringdemo.domain.store.entity.QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
        this.reviewComment = inits.isInitialized("reviewComment") ? new com.example.umc9thspringdemo.domain.review.entity.mapping.QReviewComment(forProperty("reviewComment"), inits.get("reviewComment")) : null;
    }

}

