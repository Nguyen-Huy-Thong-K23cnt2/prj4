package com.dtkt.prj4.repository;

import com.dtkt.prj4.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DTKTPaymentRepository
        extends JpaRepository<Payment, Long> {
}