package com.example.umc9th.domain.review.dto.req;

public class ReviewReqDTO {
    public record AddDTO(
            Double rating,
            String text
    ){}
}
