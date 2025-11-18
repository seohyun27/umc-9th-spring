package com.example.umc9thspringdemo.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

interface Review extends JpaRepository<com.example.umc9thspringdemo.domain.review.entity.Review, Long> {

    Review save(ReviewDTO reviewDTO) {

    }
}
