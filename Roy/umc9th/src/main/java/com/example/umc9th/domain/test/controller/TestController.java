package com.example.umc9th.domain.test.controller;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.test.converter.TestConverter;
import com.example.umc9th.domain.test.dto.TestResDTO;
import com.example.umc9th.domain.test.exception.TestException;
import com.example.umc9th.domain.test.service.query.TestQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestQueryService testQueryService;
    private final MemberRepository memberRepository;

    @GetMapping("/response")
    public ApiResponse<TestResDTO.Testing> test(@RequestParam String string){
        //응답 코드 정의
            GeneralSuccessCode code = GeneralSuccessCode.SUCCESS_CODE;
            return ApiResponse.onSuccess(code, TestConverter.toTestingDTO("your string is " + string));
    }
    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(@RequestParam Long flag)
    {
        testQueryService.checkFlag(flag);

        //응답 코드
        GeneralSuccessCode code = GeneralSuccessCode.SUCCESS_CODE;
        return ApiResponse.onSuccess(code,TestConverter.toExeceptionDTO("This is flag : " + flag));
    }
    @GetMapping("/user")
    public ApiResponse<TestResDTO.Testing> test(@AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        Member member = memberRepository.findByEmail(customUserDetails.getUsername()).orElseThrow();
        GeneralSuccessCode code = GeneralSuccessCode.SUCCESS_CODE;
        return ApiResponse.onSuccess(code,TestConverter.toTestingDTO("사용자 접근 완료! 당신의 이름은 " + member.getName() + " 입니다."));
    }
    @GetMapping("/manager")
    public ApiResponse<TestResDTO.Testing> testAdmin(@AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        Member member = memberRepository.findByEmail(customUserDetails.getUsername()).orElseThrow();
        GeneralSuccessCode code = GeneralSuccessCode.SUCCESS_CODE;
        return ApiResponse.onSuccess(code,TestConverter.toTestingDTO("관리자 접근 완료! 당신의 이름은 " + member.getName() + " 입니다."));
    }
}
