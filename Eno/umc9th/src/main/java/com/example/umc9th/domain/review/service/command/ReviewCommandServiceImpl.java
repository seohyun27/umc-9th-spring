package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.repository.ReviewPhotoRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    @Override
    public void addReview(ReviewReqDTO.AddDTO dto, Long memberId){
        // 사용자 DB에서 꺼내기
        // 가게 DB에서 꺼내기

        // 리뷰 엔티티 생성 -> 레파리토리로 save
        // dto에 사진이 존재한다면 -> 사진 엔티티 생성 -> 레파지토리로 save

        // 위의 단계에서 에러가 발생한다면 에러 처리
    }
}
