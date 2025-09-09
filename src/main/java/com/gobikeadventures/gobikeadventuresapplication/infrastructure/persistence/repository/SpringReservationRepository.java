package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringReservationRepository extends JpaRepository<Reservation, Long> {
}
