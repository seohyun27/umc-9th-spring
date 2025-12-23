package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

  @Id
  @Column(name = "mission_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "mission_point")
  @Builder.Default
  private Long point = 0L;

  @Column(name = "standard_amount")
  private Long standard_amount = 0L;

  @Column(name = "end_date",nullable = false)
  private LocalDateTime endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id",nullable = false)
  private Store store;
}
