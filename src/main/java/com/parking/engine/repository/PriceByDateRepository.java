package com.parking.engine.repository;

import com.parking.engine.entity.PriceByDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceByDateRepository extends JpaRepository<PriceByDate, Long> {
}
