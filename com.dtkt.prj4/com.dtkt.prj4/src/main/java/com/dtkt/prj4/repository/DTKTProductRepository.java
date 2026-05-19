package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DTKTProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryId(Long categoryId);
}