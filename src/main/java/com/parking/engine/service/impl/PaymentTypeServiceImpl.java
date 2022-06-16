package com.parking.engine.service.impl;

import com.parking.engine.entity.PaymentType;
import com.parking.engine.mapper.PaymentTypeMapper;
import com.parking.engine.repository.PaymentTypeRepository;
import com.parking.engine.request.PaymentTypeDTO;
import com.parking.engine.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentTypeServiceImpl implements BaseService<PaymentTypeDTO> {
    @Autowired
    PaymentTypeRepository repository;

    @Autowired
    PaymentTypeMapper mapper;

    @Override
    public boolean create(PaymentTypeDTO paymentTypeDTO) {
        try {
            PaymentType paymentType = mapper.convertDTOToEntity(paymentTypeDTO);
            repository.save(paymentType);
            return true;
        } catch (Exception ex) {
            log.error("Error when create paymentType: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<PaymentTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PaymentType> paymentTypes = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(paymentTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all paymentTypes:", e);
        }
        return false;
    }

    @Override
    public boolean update(PaymentTypeDTO paymentTypeDTO) {
        try {
            PaymentType paymentType = mapper.convertDTOToEntity(paymentTypeDTO);
            repository.save(paymentType);
            return true;
        } catch (Exception ex) {
            log.error("Error when update paymentType: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<PaymentTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PaymentType> paymentTypes = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(paymentTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all paymentTypes:", e);
        }
        return false;
    }

    @Override
    public PaymentTypeDTO findById(Long id) {
        Optional<PaymentType> paymentType = repository.findById(id);
        return paymentType.isPresent() ? mapper.convertEntityToDTO(paymentType.get()) : new PaymentTypeDTO();
    }

    @Override
    public List<PaymentTypeDTO> findAll() {
        List<PaymentType> paymentTypes = repository.findAll();
        if (null != paymentTypes && 0 < paymentTypes.size()) {
            return paymentTypes.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<PaymentTypeDTO> search(PaymentTypeDTO filter) {
        return null;
    }

    //end
}
