package com.parking.engine.mapper;

import com.parking.engine.entity.Camera;
import com.parking.engine.request.CameraDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class CameraMapper extends AbstractMapper<Camera, CameraDTO> {
    public CameraMapper() {
        super(Camera.class, CameraDTO.class);
    }
}
