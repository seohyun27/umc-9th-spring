package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // 메인 페이지: 로그인 정보가 있으면 출력
    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null) {
            return "<h1>로그인 성공!</h1><p>접속 ID: " + authentication.getName() + "</p>" +
                   "<a href='/logout'>로그아웃 하기</a>";
        }
        return "로그인 상태가 아닙니다.";
    }

    // 회원가입 요청 처리
    @PostMapping("/join")
    public RedirectView join(@RequestParam String username, @RequestParam String password) {
        userService.join(username, password);
        return new RedirectView("/login");
    }
}
