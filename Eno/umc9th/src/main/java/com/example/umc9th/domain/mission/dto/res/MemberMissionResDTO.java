package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {
    @Builder
    public record MissionFinishDTO(
            Long memberMissionId,
            boolean isCompleted,
            String missionBody,   // 미션 내용 ("아메리카노 구매 인증")
            Integer earnedPoint,  // 획득한 포인트 (가장 중요!)
            String shopName,      // 가게 이름
            LocalDateTime completedAt // 완료된 시간
    ){}
}
