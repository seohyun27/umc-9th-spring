package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private Long id;

  @Column(name = "review_rate")
  @Builder.Default
  private int rate = 0;

  @Column(name = "review_content",nullable = false)
  @Size(max = 200)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id",nullable = false)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id",nullable = false)
  private Member member;  // 리뷰 작성자

    @Column(name = "reply_content")
    private String reply;  // 답글 내용

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Size(max = 3,message = "리뷰 사진은 3개까지만 적용할 수 있습니다.")
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();

    public void addPhoto(ReviewPhoto photo) {
        reviewPhotos.add(photo);
        photo.setReview(this);
    }
}
