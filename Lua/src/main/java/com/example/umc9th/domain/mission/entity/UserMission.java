package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
@Table(name ="user_mission")
@EntityListeners(AuditingEntityListener.class)
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_success")
    @Builder.Default
    private Boolean isSuccess = false;

    @Column(name = "success_time")
    private LocalDateTime successTime;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name="mission_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;
}