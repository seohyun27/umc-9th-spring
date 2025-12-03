package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import org.springframework.data.domain.Pageable;

public interface PerformQueryService {
    MissionResDTO.previewListDTO findMyMissions(PerformReqDTO.previewListDTO dto, Pageable pageable);
}
