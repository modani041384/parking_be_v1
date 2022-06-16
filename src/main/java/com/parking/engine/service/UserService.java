package com.parking.engine.service;

import com.parking.engine.request.UserDTO;

import java.util.List;

public interface UserService extends BaseService<UserDTO> {
    boolean createRawData(UserDTO userDTO);
    boolean updateRawData(UserDTO userDTO);
    boolean createListRawData(List<UserDTO> users);
}
