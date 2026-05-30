package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface DTKTOrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUserId(
            Long userId
    );

    Optional<Order> findByIdAndUserId(
            Long orderId,
            Long userId
    );

}