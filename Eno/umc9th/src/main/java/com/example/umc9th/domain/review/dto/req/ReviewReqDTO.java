package com.example.umc9th.domain.review.dto.req;

import com.example.umc9th.domain.member.enums.Gender;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;

public class ReviewReqDTO {
    public record AddDTO(
            Double rating,
            String text
    ){}
}
