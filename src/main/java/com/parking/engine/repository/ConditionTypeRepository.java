package com.parking.engine.repository;

import com.parking.engine.entity.ConditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionTypeRepository extends JpaRepository<ConditionType, Long> {
}
