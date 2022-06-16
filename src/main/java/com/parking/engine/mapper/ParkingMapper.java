package com.parking.engine.mapper;

import com.parking.engine.entity.Parking;
import com.parking.engine.request.ParkingDTO;
import org.springframework.stereotype.Service;

@Service
public class ParkingMapper extends AbstractMapper<Parking, ParkingDTO> {
    public ParkingMapper() {
        super(Parking.class, ParkingDTO.class);
    }
}
