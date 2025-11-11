package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyMissionDto {

    // MemberMission 엔티티의 정보
    private Boolean isCompleted;
    private LocalDateTime finishAt;

    // Mission 엔티티의 정보
    private char dtype;
    private String text;
    private Integer point;
    private LocalDateTime finishDate;

    // Shop 엔티티의 정보
    private String shopName;
}

