package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.Role;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    //회원가입
    public record JoinDTO(
            @Email
            String email,
            @NotBlank
            String password,
            @NotBlank
            String name,
            Gender gender,
            Role type,
            LocalDate birth,
            String address,
            @ExistFoods
            List<Long> preferCategory
    ){}
    //로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
