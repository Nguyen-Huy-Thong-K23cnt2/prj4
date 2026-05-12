package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DTKTProductRepository extends JpaRepository<Product, Long> {
}