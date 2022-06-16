package com.parking.engine.mapper;

import com.parking.engine.entity.UserProfile;
import com.parking.engine.request.UserProfileDTO;
import org.springframework.stereotype.Service;

@Service
public class UserProfileMapper extends AbstractMapper<UserProfile, UserProfileDTO> {
    public UserProfileMapper() {
        super(UserProfile.class, UserProfileDTO.class);
    }
}
