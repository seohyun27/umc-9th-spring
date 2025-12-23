package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
