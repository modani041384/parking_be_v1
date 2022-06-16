package com.parking.engine.mapper;

import com.parking.engine.entity.PaymentDurationUnit;
import com.parking.engine.request.PaymentDurationUnitDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentDurationUnitMapper extends AbstractMapper<PaymentDurationUnit, PaymentDurationUnitDTO> {
    public PaymentDurationUnitMapper() {
        super(PaymentDurationUnit.class, PaymentDurationUnitDTO.class);
    }
}
