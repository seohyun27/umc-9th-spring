package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.converter.PerformConverter;
import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.PerformReqDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.Perform;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.PerformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformQueryServiceImpl implements PerformQueryService {

    private final PerformRepository performRepository;

    @Override
    public MissionResDTO.previewListDTO findMyMissions(PerformReqDTO.previewListDTO dto, Pageable pageable)
    {
        Page<Perform> performs = null;
        if (dto.status()!=null)
            performs = performRepository.findAllByMemberIdAndStatus(dto.memberId(),dto.status(),pageable);
        else
            performs = performRepository.findAllByMemberId(dto.memberId(),pageable);
        return PerformConverter.toPreviewList(performs);
    }
}
