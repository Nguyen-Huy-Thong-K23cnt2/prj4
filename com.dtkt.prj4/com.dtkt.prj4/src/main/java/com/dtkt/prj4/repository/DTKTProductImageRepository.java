package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DTKTProductImageRepository
        extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductId(Long productId);
}