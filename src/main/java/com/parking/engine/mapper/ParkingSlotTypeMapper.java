package com.parking.engine.mapper;

import com.parking.engine.entity.ParkingSlotType;
import com.parking.engine.request.ParkingSlotTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotTypeMapper extends AbstractMapper<ParkingSlotType, ParkingSlotTypeDTO> {
    public ParkingSlotTypeMapper() {
        super(ParkingSlotType.class, ParkingSlotTypeDTO.class);
    }
}
