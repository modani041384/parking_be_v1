package com.parking.engine.repository;

import com.parking.engine.entity.PaymentDurationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDurationUnitRepository extends JpaRepository<PaymentDurationUnit, Long> {
}
