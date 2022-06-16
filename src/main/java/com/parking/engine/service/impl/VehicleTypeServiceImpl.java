package com.parking.engine.service.impl;

import com.parking.engine.entity.VehicleType;
import com.parking.engine.mapper.VehicleTypeMapper;
import com.parking.engine.repository.VehicleTypeRepository;
import com.parking.engine.request.VehicleTypeDTO;
import com.parking.engine.service.VehicleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VehicleTypeServiceImpl implements VehicleTypeService {
    @Autowired
    VehicleTypeRepository repository;

    @Autowired
    VehicleTypeMapper mapper;

    @Override
    public boolean create(VehicleTypeDTO vehicleTypeDTO) {
        try {
            VehicleType vehicleType = mapper.convertDTOToEntity(vehicleTypeDTO);
            repository.save(vehicleType);
            return true;
        } catch (Exception ex) {
            log.error("Error when create vehicleType: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<VehicleTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<VehicleType> vehicleTypes = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(vehicleTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all vehicleTypes:", e);
        }
        return false;
    }

    @Override
    public boolean update(VehicleTypeDTO vehicleTypeDTO) {
        try {
            VehicleType vehicleType = mapper.convertDTOToEntity(vehicleTypeDTO);
            repository.save(vehicleType);
            return true;
        } catch (Exception ex) {
            log.error("Error when update vehicleType: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<VehicleTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<VehicleType> vehicleTypes = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(vehicleTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all vehicleTypes:", e);
        }
        return false;
    }

    @Override
    public VehicleTypeDTO findById(Long id) {
        Optional<VehicleType> vehicleType = repository.findById(id);
        return vehicleType.isPresent() ? mapper.convertEntityToDTO(vehicleType.get()) : new VehicleTypeDTO();
    }

    @Override
    public List<VehicleTypeDTO> findAll() {
        List<VehicleType> vehicleTypes = repository.findAll();
        if (null != vehicleTypes && 0 < vehicleTypes.size()) {
            return vehicleTypes.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<VehicleTypeDTO> search(VehicleTypeDTO filter) {
        return null;
    }

    //end
}
