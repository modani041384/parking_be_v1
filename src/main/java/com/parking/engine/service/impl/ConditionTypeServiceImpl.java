package com.parking.engine.service.impl;

import com.parking.engine.entity.ConditionType;
import com.parking.engine.mapper.ConditionTypeMapper;
import com.parking.engine.repository.ConditionTypeRepository;
import com.parking.engine.request.ConditionTypeDTO;
import com.parking.engine.service.ConditionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConditionTypeServiceImpl implements ConditionTypeService {
    @Autowired
    ConditionTypeRepository repository;

    @Autowired
    ConditionTypeMapper mapper;

    @Override
    public boolean create(ConditionTypeDTO conditionTypeDTO) {
        try {
            ConditionType conditionType = mapper.convertDTOToEntity(conditionTypeDTO);
            repository.save(conditionType);
            return true;
        } catch (Exception ex) {
            log.error("Error when create conditionType: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<ConditionTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<ConditionType> conditionTypes = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(conditionTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all conditionTypes:", e);
        }
        return false;
    }

    @Override
    public boolean update(ConditionTypeDTO conditionTypeDTO) {
        try {
            ConditionType conditionType = mapper.convertDTOToEntity(conditionTypeDTO);
            repository.save(conditionType);
            return true;
        } catch (Exception ex) {
            log.error("Error when update conditionType: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<ConditionTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<ConditionType> conditionTypes = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(conditionTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all conditionTypes:", e);
        }
        return false;
    }

    @Override
    public ConditionTypeDTO findById(Long id) {
        Optional<ConditionType> conditionType = repository.findById(id);
        return conditionType.isPresent() ? mapper.convertEntityToDTO(conditionType.get())
                : new ConditionTypeDTO();
    }

    @Override
    public List<ConditionTypeDTO> findAll() {
        List<ConditionType> conditionTypes = repository.findAll();
        if (null != conditionTypes && 0 < conditionTypes.size()) {
            return conditionTypes.stream().map(obj -> mapper.convertEntityToDTO(obj))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<ConditionTypeDTO> search(ConditionTypeDTO filter) {
        return null;
    }

    //end
}
