package com.parking.engine.repository;

import com.parking.engine.entity.PriceByHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceByHourRepository extends JpaRepository<PriceByHour, Long> {
}
