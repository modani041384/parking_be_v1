package com.parking.engine.mapper;

import com.parking.engine.entity.PriceByHour;
import com.parking.engine.request.PriceByHourDTO;
import org.springframework.stereotype.Service;

@Service
public class PriceByHourMapper extends AbstractMapper<PriceByHour, PriceByHourDTO> {
    public PriceByHourMapper() {
        super(PriceByHour.class, PriceByHourDTO.class);
    }
}
