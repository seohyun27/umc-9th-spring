package com.example.umc9thspringdemo.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1389528880L;

    public static final QMember member = new QMember("member1");

    public final com.example.umc9thspringdemo.global.entity.QBaseEntity _super = new com.example.umc9thspringdemo.global.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.example.umc9thspringdemo.domain.member.enums.Gender> gender = createEnum("gender", com.example.umc9thspringdemo.domain.member.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> inactiveDate = createDate("inactiveDate", java.time.LocalDate.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<com.example.umc9thspringdemo.domain.point.entity.PointHistory, com.example.umc9thspringdemo.domain.point.entity.QPointHistory> pointHistoryList = this.<com.example.umc9thspringdemo.domain.point.entity.PointHistory, com.example.umc9thspringdemo.domain.point.entity.QPointHistory>createList("pointHistoryList", com.example.umc9thspringdemo.domain.point.entity.PointHistory.class, com.example.umc9thspringdemo.domain.point.entity.QPointHistory.class, PathInits.DIRECT2);

    public final ListPath<SnsLogin, QSnsLogin> snsLoginList = this.<SnsLogin, QSnsLogin>createList("snsLoginList", SnsLogin.class, QSnsLogin.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

