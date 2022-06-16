package com.parking.engine.repository;

import com.parking.engine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);

    User findByUserName(String userName);

    @Transactional
    @Modifying
    @Query("update User set isDeleted = 1 where id = :id ")
    void delete(@Param("id") Long id);
}
