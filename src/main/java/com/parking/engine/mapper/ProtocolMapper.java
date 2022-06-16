package com.parking.engine.mapper;

import com.parking.engine.entity.Protocol;
import com.parking.engine.request.ProtocolDTO;
import org.springframework.stereotype.Service;

@Service
public class ProtocolMapper extends AbstractMapper<Protocol, ProtocolDTO> {
    public ProtocolMapper() {
        super(Protocol.class, ProtocolDTO.class);
    }
}
