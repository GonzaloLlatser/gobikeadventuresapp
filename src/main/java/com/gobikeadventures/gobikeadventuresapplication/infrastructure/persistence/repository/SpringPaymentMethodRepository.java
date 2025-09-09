package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringPaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
