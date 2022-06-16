package com.parking.engine.service.impl;

import com.parking.engine.entity.PaymentDurationUnit;
import com.parking.engine.mapper.PaymentDurationUnitMapper;
import com.parking.engine.repository.PaymentDurationUnitRepository;
import com.parking.engine.request.PaymentDurationUnitDTO;
import com.parking.engine.service.PaymentDurationUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentDurationUnitServiceImpl implements PaymentDurationUnitService {
    @Autowired
    PaymentDurationUnitRepository repository;

    @Autowired
    PaymentDurationUnitMapper mapper;

    @Override
    public boolean create(PaymentDurationUnitDTO paymentDurationUnitDTO) {
        try {
            PaymentDurationUnit paymentDurationUnit = mapper.convertDTOToEntity(paymentDurationUnitDTO);
            repository.save(paymentDurationUnit);
            return true;
        } catch (Exception ex) {
            log.error("Error when create paymentDurationUnit: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<PaymentDurationUnitDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PaymentDurationUnit> paymentDurationUnits =
                        list.stream().map(obj -> mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(paymentDurationUnits);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all paymentDurationUnits:", e);
        }
        return false;
    }

    @Override
    public boolean update(PaymentDurationUnitDTO paymentDurationUnitDTO) {
        try {
            PaymentDurationUnit paymentDurationUnit = mapper.convertDTOToEntity(paymentDurationUnitDTO);
            repository.save(paymentDurationUnit);
            return true;
        } catch (Exception ex) {
            log.error("Error when update paymentDurationUnit: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<PaymentDurationUnitDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PaymentDurationUnit> paymentDurationUnits =
                        list.stream().map(obj -> mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(paymentDurationUnits);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all paymentDurationUnits:", e);
        }
        return false;
    }

    @Override
    public PaymentDurationUnitDTO findById(Long id) {
        Optional<PaymentDurationUnit> paymentDurationUnit = repository.findById(id);
        return paymentDurationUnit.isPresent() ? mapper.convertEntityToDTO(paymentDurationUnit.get())
                : new PaymentDurationUnitDTO();
    }

    @Override
    public List<PaymentDurationUnitDTO> findAll() {
        List<PaymentDurationUnit> paymentDurationUnits = repository.findAll();
        if (null != paymentDurationUnits && 0 < paymentDurationUnits.size()) {
            return paymentDurationUnits.stream().map(obj -> mapper.convertEntityToDTO(obj))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<PaymentDurationUnitDTO> search(PaymentDurationUnitDTO filter) {
        return null;
    }

    //end
}
