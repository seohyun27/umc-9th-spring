package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.shop.entity.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
@Table(name ="mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_point", nullable = false)
    private Integer missionPoint;

    @Column(name = "auth_code", length = 10)
    private String authCode;

    @Column(name = "mission_content", columnDefinition = "TEXT")
    private String missionContent;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @JoinColumn(name="shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserMission> userMissionList = new ArrayList<>();
}