package com.parking.engine.service.impl;

import com.parking.engine.entity.PriceByDate;
import com.parking.engine.mapper.PriceByDateMapper;
import com.parking.engine.repository.PriceByDateRepository;
import com.parking.engine.request.PriceByDateDTO;
import com.parking.engine.service.PriceByDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PriceByDateServiceImpl implements PriceByDateService {
    @Autowired
    PriceByDateRepository repository;

    @Autowired
    PriceByDateMapper mapper;

    @Override
    public boolean create(PriceByDateDTO priceByDateDTO) {
        try {
            PriceByDate priceByDate = mapper.convertDTOToEntity(priceByDateDTO);
            repository.save(priceByDate);
            return true;
        } catch (Exception ex) {
            log.error("Error when create priceByDate: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<PriceByDateDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceByDate> priceByDates = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(priceByDates);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all priceByDates:", e);
        }
        return false;
    }

    @Override
    public boolean update(PriceByDateDTO priceByDateDTO) {
        try {
            PriceByDate priceByDate = mapper.convertDTOToEntity(priceByDateDTO);
            repository.save(priceByDate);
            return true;
        } catch (Exception ex) {
            log.error("Error when update priceByDate: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<PriceByDateDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceByDate> priceByDates = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(priceByDates);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all priceByDates:", e);
        }
        return false;
    }

    @Override
    public PriceByDateDTO findById(Long id) {
        Optional<PriceByDate> priceByDate = repository.findById(id);
        return priceByDate.isPresent() ? mapper.convertEntityToDTO(priceByDate.get()) : new PriceByDateDTO();
    }

    @Override
    public List<PriceByDateDTO> findAll() {
        List<PriceByDate> priceByDates = repository.findAll();
        if (null != priceByDates && 0 < priceByDates.size()) {
            return priceByDates.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<PriceByDateDTO> search(PriceByDateDTO filter) {
        return null;
    }

    //end
}
