package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.dto.StoreReqDTO;
import com.example.umc9th.domain.store.dto.StoreResDTO;
import com.example.umc9th.domain.store.entity.Category;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;

public class StoreConverter {

    //Entity->DTO
    public static StoreResDTO.registerDTO toRegisterDTO(Store store)
    {
        return StoreResDTO.registerDTO.builder()
                .storeId(store.getId())
                .build();
    }

    //DTO->Entity
    public static Store toStore(StoreReqDTO.registerDTO dto, Member member, Category category, Region region)
    {
        return Store.builder()
                .name(dto.name())
                .address(dto.address())
                .open_time(dto.openTime())
                .close_time(dto.closeTime())
                .member(member)
                .region(region)
                .category(category)
                .build();
    }
}
