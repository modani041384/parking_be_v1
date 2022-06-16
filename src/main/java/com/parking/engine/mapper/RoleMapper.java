package com.parking.engine.mapper;

import com.parking.engine.entity.Role;
import com.parking.engine.request.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper extends AbstractMapper<Role, RoleDTO> {
    public RoleMapper() {
        super(Role.class, RoleDTO.class);
    }
}
