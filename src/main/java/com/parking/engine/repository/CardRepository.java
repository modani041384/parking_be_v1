package com.parking.engine.repository;

import com.parking.engine.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardId(String cardId);

    @Transactional
    @Modifying
    @Query("update Card set isDeleted = 1 where id = :id ")
    void delete(@Param("id") Long id);

    //end
}
