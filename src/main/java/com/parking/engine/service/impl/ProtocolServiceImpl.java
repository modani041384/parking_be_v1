package com.parking.engine.service.impl;

import com.parking.engine.entity.Protocol;
import com.parking.engine.mapper.ProtocolMapper;
import com.parking.engine.repository.ProtocolRepository;
import com.parking.engine.request.ProtocolDTO;
import com.parking.engine.service.ProtocolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProtocolServiceImpl implements ProtocolService {
    @Autowired
    ProtocolRepository repository;

    @Autowired
    ProtocolMapper mapper;

    @Override
    public boolean create(ProtocolDTO protocolDTO) {
        try {
            Protocol protocol = mapper.convertDTOToEntity(protocolDTO);
            repository.save(protocol);
            return true;
        } catch (Exception ex) {
            log.error("Error when create protocol: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<ProtocolDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Protocol> protocols = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(protocols);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all protocols:", e);
        }
        return false;
    }

    @Override
    public boolean update(ProtocolDTO protocolDTO) {
        try {
            Protocol protocol = mapper.convertDTOToEntity(protocolDTO);
            repository.save(protocol);
            return true;
        } catch (Exception ex) {
            log.error("Error when update protocol: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<ProtocolDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Protocol> protocols = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(protocols);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all protocols:", e);
        }
        return false;
    }

    @Override
    public ProtocolDTO findById(Long id) {
        Optional<Protocol> protocol = repository.findById(id);
        return protocol.isPresent() ? mapper.convertEntityToDTO(protocol.get()) : new ProtocolDTO();
    }

    @Override
    public List<ProtocolDTO> findAll() {
        List<Protocol> protocols = repository.findAll();
        if (null != protocols && 0 < protocols.size()) {
            return protocols.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<ProtocolDTO> search(ProtocolDTO filter) {
        return null;
    }

    //end
}
