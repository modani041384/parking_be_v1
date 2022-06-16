package com.parking.engine.service.impl;

import com.parking.engine.entity.Vehicle;
import com.parking.engine.mapper.VehicleMapper;
import com.parking.engine.repository.VehicleRepository;
import com.parking.engine.request.VehicleDTO;
import com.parking.engine.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepository repository;

    @Autowired
    VehicleMapper mapper;

    @Override
    public boolean create(VehicleDTO vehicleDTO) {
        try {
            Vehicle vehicle = mapper.convertDTOToEntity(vehicleDTO);
            repository.save(vehicle);
            return true;
        } catch (Exception ex) {
            log.error("Error when create vehicle: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<VehicleDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Vehicle> vehicles = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(vehicles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all vehicles:", e);
        }
        return false;
    }

    @Override
    public boolean update(VehicleDTO vehicleDTO) {
        try {
            Vehicle vehicle = mapper.convertDTOToEntity(vehicleDTO);
            repository.save(vehicle);
            return true;
        } catch (Exception ex) {
            log.error("Error when update vehicle: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<VehicleDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Vehicle> vehicles = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(vehicles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all vehicles:", e);
        }
        return false;
    }

    @Override
    public VehicleDTO findById(Long id) {
        Optional<Vehicle> vehicle = repository.findById(id);
        return vehicle.isPresent() ? mapper.convertEntityToDTO(vehicle.get()) : new VehicleDTO();
    }

    @Override
    public List<VehicleDTO> findAll() {
        List<Vehicle> vehicles = repository.findAll();
        if (null != vehicles && 0 < vehicles.size()) {
            return vehicles.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<VehicleDTO> search(VehicleDTO filter) {
        return null;
    }

    //end
}
