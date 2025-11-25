package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.MemberType;
import com.example.umc9th.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            MemberType type,
            LocalDate birth,
            String address,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
