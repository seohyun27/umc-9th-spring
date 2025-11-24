package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {

    //Entity->DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member)
    {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreated_at())
                .build();
    }

    //DTO->Entity
    public static Member toMember(MemberReqDTO.JoinDTO dto)
    {
        return Member.builder()
                .name(dto.name())
                .birth_date(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .type(dto.type())
                .build();
    }
}
