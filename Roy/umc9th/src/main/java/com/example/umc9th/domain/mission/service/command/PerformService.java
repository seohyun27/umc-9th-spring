package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.dto.PerformResDTO;

public interface PerformService {
    PerformResDTO.registerDTO register(PerformReqDTO.registerDTO dto);
}
