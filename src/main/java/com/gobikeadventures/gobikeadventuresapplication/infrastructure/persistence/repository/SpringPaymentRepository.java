package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringPaymentRepository extends JpaRepository<Payment, Long> {
}
