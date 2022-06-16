package com.parking.engine.mapper;

import com.parking.engine.entity.MultifunctionGate;
import com.parking.engine.request.MultifunctionGateDTO;
import org.springframework.stereotype.Service;

@Service
public class MultifunctionGateMapper extends AbstractMapper<MultifunctionGate, MultifunctionGateDTO> {
    public MultifunctionGateMapper() {
        super(MultifunctionGate.class, MultifunctionGateDTO.class);
    }
}
