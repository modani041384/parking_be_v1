package com.parking.engine.mapper;

import com.parking.engine.entity.DeviceStatus;
import com.parking.engine.request.DeviceStatusDTO;
import org.springframework.stereotype.Service;

@Service
public class DeviceStatusMapper extends AbstractMapper<DeviceStatus, DeviceStatusDTO> {
    public DeviceStatusMapper() {
        super(DeviceStatus.class, DeviceStatusDTO.class);
    }
}
