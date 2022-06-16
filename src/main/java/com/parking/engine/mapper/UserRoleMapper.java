package com.parking.engine.mapper;

import com.parking.engine.entity.UserRole;
import com.parking.engine.request.UserRoleDTO;
import org.springframework.stereotype.Service;

@Service
public class UserRoleMapper extends AbstractMapper<UserRole, UserRoleDTO> {
    public UserRoleMapper() {
        super(UserRole.class, UserRoleDTO.class);
    }
}
