package com.parking.engine.service.impl;

import com.parking.engine.entity.CardAssignment;
import com.parking.engine.mapper.CardAssignmentMapper;
import com.parking.engine.repository.CardAssignmentRepository;
import com.parking.engine.request.CardAssignmentDTO;
import com.parking.engine.service.CardAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CardAssignmentServiceImpl implements CardAssignmentService {
    @Autowired
    CardAssignmentRepository repository;

    @Autowired
    CardAssignmentMapper mapper;

    @Override
    public boolean create(CardAssignmentDTO cardAssignmentDTO) {
        try {
            CardAssignment cardAssignment = mapper.convertDTOToEntity(cardAssignmentDTO);
            repository.save(cardAssignment);
            return true;
        } catch (Exception ex) {
            log.error("Error when create cardAssignment: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<CardAssignmentDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<CardAssignment> cardAssignments = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(cardAssignments);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all cardAssignments:", e);
        }
        return false;
    }

    @Override
    public boolean update(CardAssignmentDTO cardAssignmentDTO) {
        try {
            CardAssignment cardAssignment = mapper.convertDTOToEntity(cardAssignmentDTO);
            repository.save(cardAssignment);
            return true;
        } catch (Exception ex) {
            log.error("Error when update cardAssignment: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<CardAssignmentDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<CardAssignment> cardAssignments = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(cardAssignments);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all cardAssignments:", e);
        }
        return false;
    }

    @Override
    public CardAssignmentDTO findById(Long id) {
        Optional<CardAssignment> cardAssignment = repository.findById(id);
        return cardAssignment.isPresent() ? mapper.convertEntityToDTO(cardAssignment.get())
                : new CardAssignmentDTO();
    }

    @Override
    public List<CardAssignmentDTO> findAll() {
        List<CardAssignment> cardAssignments = repository.findAll();
        if (null != cardAssignments && 0 < cardAssignments.size()) {
            return cardAssignments.stream().map(obj -> mapper.convertEntityToDTO(obj))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<CardAssignmentDTO> search(CardAssignmentDTO filter) {
        return null;
    }

    //end
}
