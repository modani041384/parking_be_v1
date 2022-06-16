package com.parking.engine.mapper;

import com.parking.engine.entity.User;
import com.parking.engine.request.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper extends AbstractMapper<User, UserDTO> {
    public UserMapper() {
        super(User.class, UserDTO.class);
    }
}
