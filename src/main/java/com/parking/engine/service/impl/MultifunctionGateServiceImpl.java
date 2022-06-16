package com.parking.engine.service.impl;

import com.parking.engine.entity.MultifunctionGate;
import com.parking.engine.mapper.MultifunctionGateMapper;
import com.parking.engine.repository.MultifunctionGateRepository;
import com.parking.engine.request.MultifunctionGateDTO;
import com.parking.engine.service.MultifunctionGateService;
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
public class MultifunctionGateServiceImpl implements MultifunctionGateService {
    private final static String TABLE = "MultifunctionGate";

    @Autowired
    MultifunctionGateRepository repository;

    @Autowired
    MultifunctionGateMapper mapper;

    @Autowired
    EntityManager em;

    @Override
    public boolean create(MultifunctionGateDTO multifunctionGateDTO) {
        try {
            MultifunctionGate multifunctionGate = mapper.convertDTOToEntity(multifunctionGateDTO);
            multifunctionGate.setActive("1");
            repository.save(multifunctionGate);
            return true;
        } catch (Exception ex) {
            log.error("Error when create multifunctionGate: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<MultifunctionGateDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<MultifunctionGate> multifunctionGates = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(multifunctionGates);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all multifunctionGates:", e);
        }
        return false;
    }

    @Override
    public boolean update(MultifunctionGateDTO multifunctionGateDTO) {
        try {
            MultifunctionGate getMultifunctionGate = repository.findByMfgId(multifunctionGateDTO.getMfgId());
            if (null != getMultifunctionGate && null != getMultifunctionGate.getId()) {
                MultifunctionGate multifunctionGate = mapper.convertDTOToEntity(multifunctionGateDTO);
                multifunctionGate.setId(getMultifunctionGate.getId());
                repository.save(multifunctionGate);
                return true;
            }
        } catch (Exception ex) {
            log.error("Error when update multifunctionGate: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<MultifunctionGateDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<MultifunctionGate> multifunctionGates = new ArrayList<>();
                list.stream().forEach(obj -> {
                    MultifunctionGate getMultifunctionGate = repository.findByMfgId(obj.getMfgId());
                    if (null != getMultifunctionGate && null != getMultifunctionGate.getId()) {
                        MultifunctionGate multifunctionGate = mapper.convertDTOToEntity(obj);
                        multifunctionGate.setId(getMultifunctionGate.getId());
                        multifunctionGates.add(multifunctionGate);
                    }
                });
                if (multifunctionGates.size() > 0) {
                    //save all data
                    repository.saveAll(multifunctionGates);
                    return true;
                }
            }
        } catch (Exception e){
            log.error("Error when update all multifunctionGates:", e);
        }
        return false;
    }

    @Override
    public MultifunctionGateDTO findById(Long id) {
        Optional<MultifunctionGate> multifunctionGate = repository.findById(id);
        return multifunctionGate.isPresent() ? mapper.convertEntityToDTO(multifunctionGate.get())
                : new MultifunctionGateDTO();
    }

    @Override
    public List<MultifunctionGateDTO> findAll() {
        List<MultifunctionGate> multifunctionGates = repository.findAll();
        if (null != multifunctionGates && 0 < multifunctionGates.size()) {
            return multifunctionGates.stream().map(obj -> mapper.convertEntityToDTO(obj))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.delete(id);
            log.info("Delete multifunction gates success!!!");
            return true;
        } catch (Exception ex) {
            log.error("Error when multifunction gates user:", ex);
        }
        return false;
    }

    @Override
    public List<MultifunctionGateDTO> search(MultifunctionGateDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlEqual(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<MultifunctionGate> multifunctionGates = query.getResultList();
        if (multifunctionGates != null && multifunctionGates.size() > 0) {
            return multifunctionGates.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
