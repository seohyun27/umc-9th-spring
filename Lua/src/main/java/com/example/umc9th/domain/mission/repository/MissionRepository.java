package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN m.shop s " +
            "JOIN s.shopRegionList sr " +
            "JOIN sr.region r " +
            "WHERE r.name = :regionName " +
            "AND m.status = :status " +
            "AND m.deadline > :now")
    Page<Mission> findMissionsByRegion(
            @Param("regionName") String regionName,
            @Param("status") MissionStatus status,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );
}