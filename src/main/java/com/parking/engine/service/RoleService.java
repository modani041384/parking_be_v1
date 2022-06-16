package com.parking.engine.service;

import com.parking.engine.request.RoleDTO;

import java.util.List;

public interface RoleService extends BaseService<RoleDTO> {
    List<String> getRoles(String userId);
}
