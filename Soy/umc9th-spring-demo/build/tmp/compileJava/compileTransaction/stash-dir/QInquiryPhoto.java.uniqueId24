package com.example.umc9thspringdemo.domain.inquiry.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiryPhoto is a Querydsl query type for InquiryPhoto
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryPhoto extends EntityPathBase<InquiryPhoto> {

    private static final long serialVersionUID = 359391088L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiryPhoto inquiryPhoto = new QInquiryPhoto("inquiryPhoto");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInquiry inquiry;

    public final StringPath photoUrl = createString("photoUrl");

    public QInquiryPhoto(String variable) {
        this(InquiryPhoto.class, forVariable(variable), INITS);
    }

    public QInquiryPhoto(Path<? extends InquiryPhoto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiryPhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiryPhoto(PathMetadata metadata, PathInits inits) {
        this(InquiryPhoto.class, metadata, inits);
    }

    public QInquiryPhoto(Class<? extends InquiryPhoto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquiry = inits.isInitialized("inquiry") ? new QInquiry(forProperty("inquiry"), inits.get("inquiry")) : null;
    }

}

