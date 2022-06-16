package com.parking.engine.service.impl;

import com.parking.engine.entity.ParkingSlotType;
import com.parking.engine.mapper.ParkingSlotTypeMapper;
import com.parking.engine.repository.ParkingSlotTypeRepository;
import com.parking.engine.request.ParkingSlotTypeDTO;
import com.parking.engine.service.ParkingSlotTypeService;
import com.parking.engine.utils.SQLFilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParkingSlotTypeServiceImpl implements ParkingSlotTypeService {
    private final static String TABLE = "ParkingSlotType";

    @Autowired
    ParkingSlotTypeRepository repository;

    @Autowired
    ParkingSlotTypeMapper mapper;

    @Autowired
    EntityManager em;

    @Override
    public boolean create(ParkingSlotTypeDTO parkingSlotTypeDTO) {
        try {
            ParkingSlotType parkingSlotType = mapper.convertDTOToEntity(parkingSlotTypeDTO);
            parkingSlotType.setActive("1");
            repository.save(parkingSlotType);
            return true;
        } catch (Exception ex) {
            log.error("Error when create parkingSlotType: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<ParkingSlotTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<ParkingSlotType> parkingSlotTypes = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(parkingSlotTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all parkingSlotTypes:", e);
        }
        return false;
    }

    @Override
    public boolean update(ParkingSlotTypeDTO parkingSlotTypeDTO) {
        try {
            ParkingSlotType getParkingSlotType = repository.findByPstId(parkingSlotTypeDTO.getPstId());
            if (null != getParkingSlotType) {
                ParkingSlotType parkingSlotType = mapper.convertDTOToEntity(parkingSlotTypeDTO);
                parkingSlotType.setId(getParkingSlotType.getId());
                repository.save(parkingSlotType);
                return true;
            }
        } catch (Exception ex) {
            log.error("Error when update parkingSlotType: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<ParkingSlotTypeDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<ParkingSlotType> parkingSlotTypes = new ArrayList<>();
                list.stream().forEach(item -> {
                    ParkingSlotType getParkingSlotType = repository.findByPstId(item.getPstId());
                    if (null != getParkingSlotType) {
                        ParkingSlotType parkingSlotType = mapper.convertDTOToEntity(item);
                        parkingSlotType.setId(getParkingSlotType.getId());
                        parkingSlotTypes.add(parkingSlotType);
                    }
                });
                //save all data
                repository.saveAll(parkingSlotTypes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all parkingSlotTypes:", e);
        }
        return false;
    }

    @Override
    public ParkingSlotTypeDTO findById(Long id) {
        Optional<ParkingSlotType> parkingSlotType = repository.findById(id);
        return parkingSlotType.isPresent() ? mapper.convertEntityToDTO(parkingSlotType.get()) : new ParkingSlotTypeDTO();
    }

    @Override
    public List<ParkingSlotTypeDTO> findAll() {
        List<ParkingSlotType> parkingSlotTypes = repository.findAll();
        if (null != parkingSlotTypes && 0 < parkingSlotTypes.size()) {
            return parkingSlotTypes.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.delete(id);
            log.info("Delete parking-slot success!!!");
            return true;
        } catch (Exception ex) {
            log.error("Error when parking-slot user:", ex);
        }
        return false;
    }

    @Override
    public List<ParkingSlotTypeDTO> search(ParkingSlotTypeDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlEqual(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<ParkingSlotType> parkingSlotTypes = query.getResultList();
        if (parkingSlotTypes != null && parkingSlotTypes.size() > 0) {
            return parkingSlotTypes.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
