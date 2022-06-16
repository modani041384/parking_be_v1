package com.parking.engine.repository;

import com.parking.engine.entity.CardAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAssignmentRepository extends JpaRepository<CardAssignment, Long> {
}
