package com.parking.engine.service.impl;

import com.parking.engine.entity.SlotTypeConfig;
import com.parking.engine.mapper.SlotTypeConfigMapper;
import com.parking.engine.repository.SlotTypeConfigRepository;
import com.parking.engine.request.SlotTypeConfigDTO;
import com.parking.engine.service.SlotTypeConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SlotTypeConfigServiceImpl implements SlotTypeConfigService {
    @Autowired
    SlotTypeConfigRepository repository;

    @Autowired
    SlotTypeConfigMapper mapper;

    @Override
    public boolean create(SlotTypeConfigDTO slotTypeConfigDTO) {
        try {
            SlotTypeConfig slotTypeConfig = mapper.convertDTOToEntity(slotTypeConfigDTO);
            repository.save(slotTypeConfig);
            return true;
        } catch (Exception ex) {
            log.error("Error when create slotTypeConfig: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<SlotTypeConfigDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<SlotTypeConfig> slotTypeConfigs = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(slotTypeConfigs);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all slotTypeConfigs:", e);
        }
        return false;
    }

    @Override
    public boolean update(SlotTypeConfigDTO slotTypeConfigDTO) {
        try {
            SlotTypeConfig slotTypeConfig = mapper.convertDTOToEntity(slotTypeConfigDTO);
            repository.save(slotTypeConfig);
            return true;
        } catch (Exception ex) {
            log.error("Error when update slotTypeConfig: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<SlotTypeConfigDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<SlotTypeConfig> slotTypeConfigs = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(slotTypeConfigs);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all slotTypeConfigs:", e);
        }
        return false;
    }

    @Override
    public SlotTypeConfigDTO findById(Long id) {
        Optional<SlotTypeConfig> slotTypeConfig = repository.findById(id);
        return slotTypeConfig.isPresent() ? mapper.convertEntityToDTO(slotTypeConfig.get())
                : new SlotTypeConfigDTO();
    }

    @Override
    public List<SlotTypeConfigDTO> findAll() {
        List<SlotTypeConfig> slotTypeConfigs = repository.findAll();
        if (null != slotTypeConfigs && 0 < slotTypeConfigs.size()) {
            return slotTypeConfigs.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<SlotTypeConfigDTO> search(SlotTypeConfigDTO filter) {
        return null;
    }

    //end
}
