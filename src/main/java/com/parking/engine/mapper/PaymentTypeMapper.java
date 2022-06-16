package com.parking.engine.mapper;

import com.parking.engine.entity.PaymentType;
import com.parking.engine.request.PaymentTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeMapper extends AbstractMapper<PaymentType, PaymentTypeDTO> {
    public PaymentTypeMapper() {
        super(PaymentType.class, PaymentTypeDTO.class);
    }
}