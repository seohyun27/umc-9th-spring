package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public Page<ReviewResponseDTO.MyReviewDTO> getMyReviewList(Long userId, Long shopId, Integer rating, int page) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Page<Review> reviewPage = reviewRepository.findMyReviews(user, shopId, rating, PageRequest.of(page, 10));

        return reviewPage.map(review -> ReviewResponseDTO.MyReviewDTO.builder()
                .shopName(review.getShop().getName())
                .nickname(user.getNickname())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build());
    }
}