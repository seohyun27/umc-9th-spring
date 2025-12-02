package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;

public interface MissionService {
    MissionResDTO.registerDTO register(MissionReqDTO.registerDTO dto);
}
