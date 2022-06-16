package com.parking.engine.repository;

import com.parking.engine.entity.MultifunctionGate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MultifunctionGateRepository extends JpaRepository<MultifunctionGate, Long> {
    MultifunctionGate findByMfgId(String mfgId);

    @Transactional
    @Modifying
    @Query("update MultifunctionGate set isDeleted = 1 where id = :id ")
    void delete(@Param("id") Long id);
}
