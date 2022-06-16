package com.parking.engine.mapper;

import com.parking.engine.entity.ParkingLane;
import com.parking.engine.request.ParkingLaneDTO;
import org.springframework.stereotype.Service;

@Service
public class ParkingLaneMapper extends AbstractMapper<ParkingLane, ParkingLaneDTO> {
    public ParkingLaneMapper() {
        super(ParkingLane.class, ParkingLaneDTO.class);
    }
}
