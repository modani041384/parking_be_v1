package com.parking.engine.mapper;

import com.parking.engine.entity.VehicleType;
import com.parking.engine.request.VehicleTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeMapper extends AbstractMapper<VehicleType, VehicleTypeDTO> {
    public VehicleTypeMapper() {
        super(VehicleType.class, VehicleTypeDTO.class);
    }
}
