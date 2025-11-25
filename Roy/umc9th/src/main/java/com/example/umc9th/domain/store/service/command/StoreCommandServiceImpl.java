package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.entity.Category;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.CategoryRepository;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    //회원가입
    @Override
    public StoreResDTO.registerDTO register(StoreReqDTO.registerDTO dto)
    {
        Member member = memberRepository.findById(dto.memberId()).orElse(null);
        Region region = regionRepository.findById(dto.regionId()).orElse(null);
        Category category = categoryRepository.findById(dto.categoryId()).orElse(null);
        Store store = StoreConverter.toStore(dto,member,category,region);
        storeRepository.save(store);
        return StoreConverter.toRegisterDTO(store);
    }
}
