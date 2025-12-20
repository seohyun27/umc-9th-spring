package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.registerDTO register(MissionReqDTO.registerDTO dto)
    {
        // dto의 가게의 매니저가 dto의 매니저 아이디와 같은 지 확인
        Store store = storeRepository.findById(dto.storeId()).orElse(null);
        if (store == null) throw new StoreException(StoreErrorCode.NOT_FOUND);
        Long storeManagerId = store.getMember().getId();
        Long requestManagerId = dto.managerId();
        if (!Objects.equals(storeManagerId, requestManagerId)) throw new StoreException(StoreErrorCode.UNAUTHORIZED);
        Mission mission = MissionConverter.toMission(dto,store);
        missionRepository.save(mission);
        return MissionConverter.toRegisterDTO(mission);
    }
}
