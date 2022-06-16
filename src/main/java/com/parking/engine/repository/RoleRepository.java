package com.parking.engine.repository;

import com.parking.engine.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select name from role inner join user_role on user_role.role_id = role.id " +
            " inner join user on user.user_id = user_role.user_id where user.user_id = :userId" , nativeQuery = true)
    List<String> getRole(@Param("userId") String userId);
}
