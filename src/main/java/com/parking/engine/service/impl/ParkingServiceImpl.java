package com.parking.engine.service.impl;

import com.parking.engine.entity.Parking;
import com.parking.engine.mapper.ParkingMapper;
import com.parking.engine.repository.ParkingRepository;
import com.parking.engine.request.ParkingDTO;
import com.parking.engine.service.ParkingService;
import com.parking.engine.utils.SQLFilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {
    private final static String TABLE = "Parking";

    @Autowired
    ParkingRepository repository;

    @Autowired
    ParkingMapper mapper;

    @Autowired
    EntityManager em;

    @Override
    public boolean create(ParkingDTO parkingDTO) {
        try {
            Parking parking = mapper.convertDTOToEntity(parkingDTO);
            String parkingId = UUID.randomUUID().toString();
            parking.setParkingId(parkingId);
            parking.setActive("1");
            repository.save(parking);
            return true;
        } catch (Exception ex) {
            log.error("Error when create parking: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<ParkingDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Parking> parking = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(parking);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all parking:", e);
        }
        return false;
    }

    @Override
    public boolean update(ParkingDTO parkingDTO) {
        try {
            Parking parking = repository.findByParkingId(parkingDTO.getParkingId());
            if (null == parkingDTO) return false;
            //set value
            if (null != parkingDTO.getName()) parking.setName(parkingDTO.getName());
            if (null != parkingDTO.getAddress()) parking.setAddress(parkingDTO.getAddress());
            if (null != parkingDTO.getArea()) parking.setArea(parkingDTO.getArea());
            if (null != parkingDTO.getParkingLanes())
                parking.setParkingLanes(parkingDTO.getParkingLanes().stream().collect(Collectors.toSet()));
            if (null != parkingDTO.getSlotTypeConfigs())
                parking.setSlotTypeConfigs(parkingDTO.getSlotTypeConfigs().stream().collect(Collectors.toSet()));
            if (null != parkingDTO.getQuantity()) parking.setQuantity(parkingDTO.getQuantity());
            if (null != parkingDTO.getCardAssignments())
                parking.setCardAssignments(parkingDTO.getCardAssignments().stream().collect(Collectors.toSet()));
            if (null != parkingDTO.getPriceBooks())
                parking.setPriceBook(parkingDTO.getPriceBooks().stream().collect(Collectors.toSet()));
            //update
            repository.save(parking);
            return true;
        } catch (Exception ex) {
            log.error("Error when update parking: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<ParkingDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Parking> parking = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(parking);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all parking:", e);
        }
        return false;
    }

    @Override
    public ParkingDTO findById(Long id) {
        Optional<Parking> parking = repository.findById(id);
        return parking.isPresent() ? mapper.convertEntityToDTO(parking.get()) : new ParkingDTO();
    }

    @Override
    public List<ParkingDTO> findAll() {
        List<Parking> parking = repository.findAll();
        if (null != parking && 0 < parking.size()) {
            return parking.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.delete(id);
            log.info("Delete parking success!!!");
            return true;
        } catch (Exception ex) {
            log.error("Error when parking user:", ex);
        }
        return false;
    }

    @Override
    public List<ParkingDTO> search(ParkingDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlEqual(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<Parking> lstParking = query.getResultList();
        if (lstParking != null && lstParking.size() > 0) {
            return lstParking.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
