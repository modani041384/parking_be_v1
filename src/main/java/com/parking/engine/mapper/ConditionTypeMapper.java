package com.parking.engine.mapper;

import com.parking.engine.entity.ConditionType;
import com.parking.engine.request.ConditionTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class ConditionTypeMapper extends AbstractMapper<ConditionType, ConditionTypeDTO> {
    public ConditionTypeMapper() {
        super(ConditionType.class, ConditionTypeDTO.class);
    }
}