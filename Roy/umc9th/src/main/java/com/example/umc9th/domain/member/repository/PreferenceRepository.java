package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference,Long> {
}
