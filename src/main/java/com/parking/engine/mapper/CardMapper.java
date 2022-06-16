package com.parking.engine.mapper;

import com.parking.engine.entity.Card;
import com.parking.engine.request.CardDTO;
import org.springframework.stereotype.Service;

@Service
public class CardMapper extends AbstractMapper<Card, CardDTO> {
    public CardMapper() {
        super(Card.class, CardDTO.class);
    }
}
