package com.parking.engine.mapper;

import com.parking.engine.entity.CardAssignment;
import com.parking.engine.request.CardAssignmentDTO;
import org.springframework.stereotype.Service;

@Service
public class CardAssignmentMapper extends AbstractMapper<CardAssignment, CardAssignmentDTO> {
    public CardAssignmentMapper() {
        super(CardAssignment.class, CardAssignmentDTO.class);
    }
}
