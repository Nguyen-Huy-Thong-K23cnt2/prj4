package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DTKTCategoryRepository extends JpaRepository<Category, Long> {
}