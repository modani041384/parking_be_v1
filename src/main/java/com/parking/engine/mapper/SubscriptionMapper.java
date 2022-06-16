package com.parking.engine.mapper;

import com.parking.engine.entity.Subscription;
import com.parking.engine.request.SubscriptionDTO;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionMapper extends AbstractMapper<Subscription, SubscriptionDTO> {
    public SubscriptionMapper() {
        super(Subscription.class, SubscriptionDTO.class);
    }
}
