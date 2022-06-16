package com.parking.engine.service.impl;

import com.parking.engine.entity.PriceBookAssignment;
import com.parking.engine.mapper.PriceBookAssignmentMapper;
import com.parking.engine.repository.PriceBookAssignmentRepository;
import com.parking.engine.request.PriceBookAssignmentDTO;
import com.parking.engine.service.PriceBookAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PriceBookAssignmentServiceImpl implements PriceBookAssignmentService {
    @Autowired
    PriceBookAssignmentRepository repository;

    @Autowired
    PriceBookAssignmentMapper mapper;

    @Override
    public boolean create(PriceBookAssignmentDTO priceBookAssignmentDTO) {
        try {
            PriceBookAssignment priceBookAssignment = mapper.convertDTOToEntity(priceBookAssignmentDTO);
            repository.save(priceBookAssignment);
            return true;
        } catch (Exception ex) {
            log.error("Error when create priceBookAssignment: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<PriceBookAssignmentDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceBookAssignment> priceBookAssignments = list.stream().map(obj ->
                                mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(priceBookAssignments);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all priceBookAssignments:", e);
        }
        return false;
    }

    @Override
    public boolean update(PriceBookAssignmentDTO priceBookAssignmentDTO) {
        try {
            PriceBookAssignment priceBookAssignment = mapper.convertDTOToEntity(priceBookAssignmentDTO);
            repository.save(priceBookAssignment);
            return true;
        } catch (Exception ex) {
            log.error("Error when update priceBookAssignment: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<PriceBookAssignmentDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceBookAssignment> priceBookAssignments = list.stream().map(obj ->
                                mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(priceBookAssignments);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all priceBookAssignments:", e);
        }
        return false;
    }

    @Override
    public PriceBookAssignmentDTO findById(Long id) {
        Optional<PriceBookAssignment> priceBookAssignment = repository.findById(id);
        return priceBookAssignment.isPresent() ? mapper.convertEntityToDTO(priceBookAssignment.get())
                : new PriceBookAssignmentDTO();
    }

    @Override
    public List<PriceBookAssignmentDTO> findAll() {
        List<PriceBookAssignment> priceBookAssignments = repository.findAll();
        if (null != priceBookAssignments && 0 < priceBookAssignments.size()) {
            return priceBookAssignments.stream().map(obj -> mapper.convertEntityToDTO(obj))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<PriceBookAssignmentDTO> search(PriceBookAssignmentDTO filter) {
        return null;
    }

    //end
}
