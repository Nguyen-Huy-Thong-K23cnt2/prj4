package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DTKTOrderRepository
        extends JpaRepository<Order, Long> {
}