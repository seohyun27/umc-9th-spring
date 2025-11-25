package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.review.repository.ReviewPhotoRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.registerDTO register(ReviewReqDTO.registerDTO dto){
        Member member = memberRepository.findById(dto.memberId()).orElse(null);
        Store store = storeRepository.findById(dto.storeId()).orElse(null);
        List<ReviewPhoto> photoList = dto.reviewPhotos().stream()
                .map(url -> ReviewPhoto.builder().url(url).build())
                .toList();
        Review review = ReviewConverter.toReview(dto, member, photoList, store);
        photoList.forEach(review::addPhoto);
        reviewRepository.save(review);
        return ReviewConverter.toRegisterDTO(review);
    }
}
