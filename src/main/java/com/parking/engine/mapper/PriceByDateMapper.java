package com.parking.engine.mapper;

import com.parking.engine.entity.PriceByDate;
import com.parking.engine.request.PriceByDateDTO;
import org.springframework.stereotype.Service;

@Service
public class PriceByDateMapper extends AbstractMapper<PriceByDate, PriceByDateDTO> {
    public PriceByDateMapper() {
        super(PriceByDate.class, PriceByDateDTO.class);
    }
}
