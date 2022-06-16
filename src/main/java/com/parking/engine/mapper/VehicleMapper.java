package com.parking.engine.mapper;

import com.parking.engine.entity.Vehicle;
import com.parking.engine.request.VehicleDTO;
import org.springframework.stereotype.Service;

@Service
public class VehicleMapper extends AbstractMapper<Vehicle, VehicleDTO> {
    public VehicleMapper() {
        super(Vehicle.class, VehicleDTO.class);
    }
}
