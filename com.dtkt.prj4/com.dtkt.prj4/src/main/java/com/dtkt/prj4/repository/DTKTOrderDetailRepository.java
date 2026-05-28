package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DTKTOrderDetailRepository
        extends JpaRepository<OrderDetail, Long> {
}