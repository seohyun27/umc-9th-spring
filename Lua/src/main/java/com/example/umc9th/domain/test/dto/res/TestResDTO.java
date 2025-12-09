package com.example.umc9th.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;
//DTO는 큰 묶음으로 클래스를 만들고, 내부적으로 static클래스를 만드는 게 좋다.
public class TestResDTO {

    @Builder
    @Getter
    public static class Testing{
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}
