package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberQueryService {
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO loginDTO){return null;}
}
