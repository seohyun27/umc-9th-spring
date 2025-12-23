package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.Preference;
import com.example.umc9th.domain.member.enums.Role;
import com.example.umc9th.domain.member.exception.FoodException;
import com.example.umc9th.domain.member.exception.code.FoodErrorCode;
import com.example.umc9th.domain.member.repository.FoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member.repository.PreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final PreferenceRepository preferenceRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto)
    {
        String salt = passwordEncoder.encode(dto.password());
        Member member = MemberConverter.toMember(dto,salt);
        
        //DB 적용
        memberRepository.save(member);

        if (dto.preferCategory().size() > 1){
            List<Preference> preferenceList = new ArrayList<>();
            for (Long id: dto.preferCategory())
            {
                Food food = foodRepository.findById(id)
                        .orElseThrow(()->new FoodException(FoodErrorCode.NOT_FOUND));
                Preference preference = Preference.builder()
                        .member(member)
                        .food(food)
                        .build();
                preferenceList.add(preference);
            }
            preferenceRepository.saveAll(preferenceList);
        }
        return MemberConverter.toJoinDTO(member);
    }
}
