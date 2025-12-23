package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    MissionResDTO.previewListDTO findMissionsByStore(MissionReqDTO.previewListDTO dto, Pageable pageable);
}
