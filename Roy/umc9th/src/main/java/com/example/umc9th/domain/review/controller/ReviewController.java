package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    public ReviewController(ReviewQueryService reviewQueryService)
    {
        this.reviewQueryService = reviewQueryService;
    }
    @GetMapping("reviews/search")
    public List<ReviewDto> searchReview(@RequestParam String query, @RequestParam String type){
        return reviewQueryService.searchReview(query,type);
    }
}
