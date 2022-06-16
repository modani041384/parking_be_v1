package com.parking.engine.service.impl;

import com.parking.engine.entity.ParkingLane;
import com.parking.engine.mapper.ParkingLaneMapper;
import com.parking.engine.repository.ParkingLaneRepository;
import com.parking.engine.request.ParkingLaneDTO;
import com.parking.engine.service.ParkingLaneService;
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
public class ParkingLaneServiceImpl implements ParkingLaneService {
    private final static String TABLE = "ParkingLane";

    @Autowired
    ParkingLaneRepository repository;

    @Autowired
    ParkingLaneMapper mapper;

    @Autowired
    EntityManager em;

    @Override
    public boolean create(ParkingLaneDTO parkingLaneDTO) {
        try {
            ParkingLane parkingLane = mapper.convertDTOToEntity(parkingLaneDTO);
            parkingLane.setActive("1");
            repository.save(parkingLane);
            return true;
        } catch (Exception ex) {
            log.error("Error when create parkingLane: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<ParkingLaneDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<ParkingLane> parkingLanes = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(parkingLanes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all parkingLanes:", e);
        }
        return false;
    }

    @Override
    public boolean update(ParkingLaneDTO parkingLaneDTO) {
        try {
            ParkingLane parkingLane = repository.findByParkingLaneId(parkingLaneDTO.getParkingLaneId());
            if(parkingLane == null) return false;
            parkingLane.setName(parkingLaneDTO.getName());
            repository.save(parkingLane);
            return true;
        } catch (Exception ex) {
            log.error("Error when update parkingLane: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<ParkingLaneDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<ParkingLane> parkingLanes = new ArrayList<>();
                list.stream().forEach(obj -> {
                    ParkingLane parkingLane = repository.findByParkingLaneId(obj.getParkingLaneId());
                    if (parkingLane != null) {
                        parkingLane.setName(obj.getName());
                        parkingLanes.add(parkingLane);
                    }
                });
                //save all data
                repository.saveAll(parkingLanes);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all parkingLanes:", e);
        }
        return false;
    }

    @Override
    public ParkingLaneDTO findById(Long id) {
        Optional<ParkingLane> parkingLane = repository.findById(id);
        return parkingLane.isPresent() ? mapper.convertEntityToDTO(parkingLane.get()) : new ParkingLaneDTO();
    }

    @Override
    public List<ParkingLaneDTO> findAll() {
        List<ParkingLane> parkingLanes = repository.findAll();
        if (null != parkingLanes && 0 < parkingLanes.size()) {
            return parkingLanes.stream().map(obj -> mapper.convertEntityToDTO(obj))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.delete(id);
            log.info("Delete parking-lane success!!!");
            return true;
        } catch (Exception ex) {
            log.error("Error when delete parking-lane:", ex);
        }
        return false;
    }

    @Override
    public List<ParkingLaneDTO> search(ParkingLaneDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlEqual(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<ParkingLane> parkingLanes = query.getResultList();
        if (parkingLanes != null && parkingLanes.size() > 0) {
            return parkingLanes.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
