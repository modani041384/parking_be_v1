package com.parking.engine.service.impl;

import com.parking.engine.entity.Camera;
import com.parking.engine.mapper.CameraMapper;
import com.parking.engine.mapper.MapperUtils;
import com.parking.engine.repository.CameraRepository;
import com.parking.engine.request.CameraDTO;
import com.parking.engine.service.CameraService;
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
public class CameraServiceImpl implements CameraService {
    private final static String TABLE = "Camera";

    @Autowired
    CameraRepository repository;

    @Autowired
    CameraMapper mapper;

    @Autowired
    EntityManager em;

    @Override
    public boolean create(CameraDTO cameraDTO) {
        try {
            Camera camera = mapper.convertDTOToEntity(cameraDTO);
            camera.setActive("1");
            repository.save(camera);
            return true;
        } catch (Exception ex) {
            log.error("Error when create camera: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<CameraDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Camera> cameras = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(cameras);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all camera:", e);
        }
        return false;
    }

    @Override
    public boolean update(CameraDTO cameraDTO) {
        try {
            Camera getCamera = repository.findByCameraId(cameraDTO.getCameraId());
            if (null != getCamera && null != getCamera.getId()) {
                Camera camera = mapper.convertDTOToEntity(cameraDTO);
                camera.setId(getCamera.getId());
                repository.save(camera);
                return true;
            }
        } catch (Exception ex) {
            log.error("Error when update camera: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<CameraDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Camera> cameras = new ArrayList<>();
                list.stream().forEach(obj -> {
                    Camera getCamera = repository.findByCameraId(obj.getCameraId());
                    if (null != getCamera && null != getCamera.getId()) {
                        Camera camera = mapper.convertDTOToEntity(obj);
                        camera.setId(getCamera.getId());
                        cameras.add(camera);
                    }
                });
                if (cameras.size() > 0) {
                    //save all data
                    repository.saveAll(cameras);
                    return true;
                }
            }
        } catch (Exception e){
            log.error("Error when update all camera:", e);
        }
        return false;
    }

    @Override
    public CameraDTO findById(Long id) {
        Optional<Camera> entity = repository.findById(id);
        if (entity.isPresent()) {
            Camera camera = entity.get();
            //get parking lane name
            MapperUtils.getParkingLaneNameInit(camera);
            return mapper.convertEntityToDTO(entity.get());
        }
        return new CameraDTO();
    }

    @Override
    public List<CameraDTO> findAll() {
        List<Camera> cameras = repository.findAll();
        if (null != cameras && 0 < cameras.size()) {
            return cameras.stream().map(obj -> {
                //get parking lane name
                MapperUtils.getParkingLaneNameInit(obj);
                return mapper.convertEntityToDTO(obj);
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.delete(id);
            log.info("Delete camera success!!!");
            return true;
        } catch (Exception ex) {
            log.error("Error when camera user:", ex);
        }
        return false;
    }

    @Override
    public List<CameraDTO> search(CameraDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlLike(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<Camera> cameras = query.getResultList();
        if (cameras != null && cameras.size() > 0) {
            return cameras.stream().map(obj -> {
                //get parking lane name
                MapperUtils.getParkingLaneNameInit(obj);
                return mapper.convertEntityToDTO(obj);
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
