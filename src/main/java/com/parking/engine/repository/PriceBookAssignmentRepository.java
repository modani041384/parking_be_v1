package com.parking.engine.repository;

import com.parking.engine.entity.PriceBookAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceBookAssignmentRepository extends JpaRepository<PriceBookAssignment, Long> {
}
