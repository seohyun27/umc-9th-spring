package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            List<Long> preferCategory   // 선호 음식 카테고리 정보들을 리스트로 받아옴
    ){}
}
