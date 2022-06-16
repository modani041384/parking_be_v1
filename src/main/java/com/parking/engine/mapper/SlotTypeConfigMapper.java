package com.parking.engine.mapper;

import com.parking.engine.entity.SlotTypeConfig;
import com.parking.engine.request.SlotTypeConfigDTO;
import org.springframework.stereotype.Service;

@Service
public class SlotTypeConfigMapper extends AbstractMapper<SlotTypeConfig, SlotTypeConfigDTO> {
    public SlotTypeConfigMapper() {
        super(SlotTypeConfig.class, SlotTypeConfigDTO.class);
    }
}
