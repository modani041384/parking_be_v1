package com.parking.engine.response;

import com.parking.engine.request.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO extends ResponseDTO<UserDTO> {
    private String accessToken;
    private String tokenType;
    private List<String> roles;
}
