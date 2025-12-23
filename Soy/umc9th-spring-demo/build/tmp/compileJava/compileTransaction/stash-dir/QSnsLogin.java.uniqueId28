package com.example.umc9thspringdemo.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSnsLogin is a Querydsl query type for SnsLogin
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSnsLogin extends EntityPathBase<SnsLogin> {

    private static final long serialVersionUID = 1813849479L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSnsLogin snsLogin = new QSnsLogin("snsLogin");

    public final com.example.umc9thspringdemo.global.entity.QBaseEntity _super = new com.example.umc9thspringdemo.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginValue = createString("loginValue");

    public final QMember member;

    public final StringPath platformName = createString("platformName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSnsLogin(String variable) {
        this(SnsLogin.class, forVariable(variable), INITS);
    }

    public QSnsLogin(Path<? extends SnsLogin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSnsLogin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSnsLogin(PathMetadata metadata, PathInits inits) {
        this(SnsLogin.class, metadata, inits);
    }

    public QSnsLogin(Class<? extends SnsLogin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

