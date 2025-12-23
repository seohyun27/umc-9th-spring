package com.example.umc9th.domain.test.converter;

import com.example.umc9th.domain.test.dto.TestResDTO;

public class TestConverter {
    public static TestResDTO.Testing toTestingDTO(String testing){
        return TestResDTO.Testing.builder().testing(testing).build();
    }
    public static TestResDTO.Exception toExeceptionDTO(String testing){
        return TestResDTO.Exception.builder().testing(testing).build();
    }
}
