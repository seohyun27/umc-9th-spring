package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    MissionResDTO.MissionPreViewListDTO findShopMission(Long shopId, Pageable pageable);
}
