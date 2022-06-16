package com.parking.engine.service.impl;

import com.parking.engine.entity.PriceByHour;
import com.parking.engine.mapper.PriceByHourMapper;
import com.parking.engine.repository.PriceByHourRepository;
import com.parking.engine.request.PriceByHourDTO;
import com.parking.engine.service.PriceByHourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PriceByHourServiceImpl implements PriceByHourService {
    @Autowired
    PriceByHourRepository repository;

    @Autowired
    PriceByHourMapper mapper;

    @Override
    public boolean create(PriceByHourDTO priceByHourDTO) {
        try {
            PriceByHour priceByHour = mapper.convertDTOToEntity(priceByHourDTO);
            repository.save(priceByHour);
            return true;
        } catch (Exception ex) {
            log.error("Error when create priceByHour: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<PriceByHourDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceByHour> priceByHours = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(priceByHours);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all priceByHours:", e);
        }
        return false;
    }

    @Override
    public boolean update(PriceByHourDTO priceByHourDTO) {
        try {
            PriceByHour priceByHour = mapper.convertDTOToEntity(priceByHourDTO);
            repository.save(priceByHour);
            return true;
        } catch (Exception ex) {
            log.error("Error when update priceByHour: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<PriceByHourDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceByHour> priceByHours = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(priceByHours);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all priceByHours:", e);
        }
        return false;
    }

    @Override
    public PriceByHourDTO findById(Long id) {
        Optional<PriceByHour> priceByHour = repository.findById(id);
        return priceByHour.isPresent() ? mapper.convertEntityToDTO(priceByHour.get()) : new PriceByHourDTO();
    }

    @Override
    public List<PriceByHourDTO> findAll() {
        List<PriceByHour> priceByHours = repository.findAll();
        if (null != priceByHours && 0 < priceByHours.size()) {
            return priceByHours.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<PriceByHourDTO> search(PriceByHourDTO filter) {
        return null;
    }

    //end
}
