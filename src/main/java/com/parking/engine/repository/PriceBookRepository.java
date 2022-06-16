package com.parking.engine.repository;

import com.parking.engine.entity.PriceBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceBookRepository extends JpaRepository<PriceBook, Long> {
}
