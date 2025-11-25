package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "store")
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="manager_id",nullable = false)
  private Member member;

  @Column(name = "store_address", length = 50, nullable = false)
  private String address;

  @Column(name = "store_name", length = 10, nullable = false)
  private String name;

  @Column(name = "open_time")
  private LocalTime open_time;

  @Column(name = "close_time")
  private LocalTime close_time;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "region_id")
  private Region region;
}
