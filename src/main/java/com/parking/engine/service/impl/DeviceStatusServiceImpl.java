package com.parking.engine.service.impl;

import com.parking.engine.entity.DeviceStatus;
import com.parking.engine.mapper.DeviceStatusMapper;
import com.parking.engine.repository.DeviceStatusRepository;
import com.parking.engine.request.DeviceStatusDTO;
import com.parking.engine.service.DeviceStatusService;
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
public class DeviceStatusServiceImpl implements DeviceStatusService {
    private final static String TABLE = "DeviceStatus";

    @Autowired
    DeviceStatusRepository repository;

    @Autowired
    DeviceStatusMapper mapper;

    @Autowired
    EntityManager em;

    @Override
    public boolean create(DeviceStatusDTO deviceStatusDTO) {
        try {
            DeviceStatus deviceStatus = mapper.convertDTOToEntity(deviceStatusDTO);
            repository.save(deviceStatus);
            return true;
        } catch (Exception ex) {
            log.error("Error when create deviceStatus: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<DeviceStatusDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<DeviceStatus> deviceStatuses = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(deviceStatuses);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all deviceStatuses:", e);
        }
        return false;
    }

    @Override
    public boolean update(DeviceStatusDTO deviceStatusDTO) {
        try {
            DeviceStatus deviceStatus = mapper.convertDTOToEntity(deviceStatusDTO);
            repository.save(deviceStatus);
            return true;
        } catch (Exception ex) {
            log.error("Error when update deviceStatus: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<DeviceStatusDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<DeviceStatus> deviceStatuses = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(deviceStatuses);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all device-status:", e);
        }
        return false;
    }

    @Override
    public DeviceStatusDTO findById(Long id) {
        Optional<DeviceStatus> deviceStatus = repository.findById(id);
        return deviceStatus.isPresent() ? mapper.convertEntityToDTO(deviceStatus.get())
                : new DeviceStatusDTO();
    }

    @Override
    public List<DeviceStatusDTO> findAll() {
        List<DeviceStatus> deviceStatuses = repository.findAll();
        if (null != deviceStatuses && 0 < deviceStatuses.size()) {
            return deviceStatuses.stream().map(obj -> mapper.convertEntityToDTO(obj))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.delete(id);
            log.info("Delete device-status success!!!");
            return true;
        } catch (Exception ex) {
            log.error("Error when delete device-status:", ex);
        }
        return false;
    }

    @Override
    public List<DeviceStatusDTO> search(DeviceStatusDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlLike(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<DeviceStatus> deviceStatuses = query.getResultList();
        if (deviceStatuses != null && deviceStatuses.size() > 0) {
            return deviceStatuses.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
