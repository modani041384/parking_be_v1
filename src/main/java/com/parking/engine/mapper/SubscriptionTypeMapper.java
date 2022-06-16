package com.parking.engine.mapper;

import com.parking.engine.entity.SubscriptionType;
import com.parking.engine.request.SubscriptionTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionTypeMapper extends AbstractMapper<SubscriptionType, SubscriptionTypeDTO> {
    public SubscriptionTypeMapper() {
        super(SubscriptionType.class, SubscriptionTypeDTO.class);
    }
}
