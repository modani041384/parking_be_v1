package com.parking.engine.service.impl;

import com.parking.engine.entity.SubscriptionType;
import com.parking.engine.mapper.SubscriptionTypeMapper;
import com.parking.engine.repository.SubscriptionTypeRepository;
import com.parking.engine.request.SubscriptionTypeDTO;
import com.parking.engine.service.SubscriptionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {
    @Autowired
    SubscriptionTypeRepository repository;

    @Autowired
    SubscriptionTypeMapper mapper;

    @Override
    public boolean create(SubscriptionTypeDTO subscriptionTypeDTO) {
        try {
            SubscriptionType subscriptionType = mapper.convertDTOToEntity(subscriptionTypeDTO);
            repository.save(subscriptionType);
            return true;
        } catch (Exception ex) {
            log.error("Error when create subscriptionType: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<SubscriptionTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<SubscriptionType> subscriptionTypes = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(subscriptionTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all subscriptionTypes:", e);
        }
        return false;
    }

    @Override
    public boolean update(SubscriptionTypeDTO subscriptionTypeDTO) {
        try {
            SubscriptionType subscriptionType = mapper.convertDTOToEntity(subscriptionTypeDTO);
            repository.save(subscriptionType);
            return true;
        } catch (Exception ex) {
            log.error("Error when update subscriptionType: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<SubscriptionTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<SubscriptionType> subscriptionTypes = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(subscriptionTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all subscriptionTypes:", e);
        }
        return false;
    }

    @Override
    public SubscriptionTypeDTO findById(Long id) {
        Optional<SubscriptionType> subscriptionType = repository.findById(id);
        return subscriptionType.isPresent() ? mapper.convertEntityToDTO(subscriptionType.get())
                : new SubscriptionTypeDTO();
    }

    @Override
    public List<SubscriptionTypeDTO> findAll() {
        List<SubscriptionType> subscriptionTypes = repository.findAll();
        if (null != subscriptionTypes && 0 < subscriptionTypes.size()) {
            return subscriptionTypes.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<SubscriptionTypeDTO> search(SubscriptionTypeDTO filter) {
        return null;
    }

    //end
}
