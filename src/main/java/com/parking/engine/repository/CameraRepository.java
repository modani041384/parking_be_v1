package com.parking.engine.repository;

import com.parking.engine.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long>, JpaSpecificationExecutor<Camera> {
    Camera findByCameraId(String cameraId);

    @Transactional
    @Modifying
    @Query("update Camera set isDeleted = 1 where id = :id ")
    void delete(@Param("id") Long id);
}
