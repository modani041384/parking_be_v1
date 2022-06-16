package com.parking.engine.mapper;

import com.parking.engine.entity.PriceBookAssignment;
import com.parking.engine.request.PriceBookAssignmentDTO;
import org.springframework.stereotype.Service;

@Service
public class PriceBookAssignmentMapper  extends AbstractMapper<PriceBookAssignment, PriceBookAssignmentDTO> {
    public PriceBookAssignmentMapper() {
        super(PriceBookAssignment.class, PriceBookAssignmentDTO.class);
    }
}
