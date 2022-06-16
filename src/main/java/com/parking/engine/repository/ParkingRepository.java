package com.parking.engine.repository;

import com.parking.engine.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    @Query(value = "select * from parking where name like concat('%',:name,'%') and area like concat('%',:area,'%') ", nativeQuery = true)
    List<Parking> findByNameAndArea(@Param("name")String name, @Param("area")String area);
    /**
     * Find by parkingId
     * @param parkingId
     * @return
     */
    Parking findByParkingId(String parkingId);

    @Transactional
    @Modifying
    @Query("update Parking set isDeleted = 1 where id = :id ")
    void delete(@Param("id") Long id);
}
