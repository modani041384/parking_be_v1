package com.parking.engine.repository;

import com.parking.engine.entity.SlotTypeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotTypeConfigRepository extends JpaRepository<SlotTypeConfig, Long> {
}
