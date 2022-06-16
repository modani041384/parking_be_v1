package com.parking.engine.service.impl;

import com.parking.engine.entity.PriceBook;
import com.parking.engine.mapper.PriceBookMapper;
import com.parking.engine.repository.PriceBookRepository;
import com.parking.engine.request.PriceBookDTO;
import com.parking.engine.service.PriceBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PriceBookServiceImpl implements PriceBookService {
    @Autowired
    PriceBookRepository repository;

    @Autowired
    PriceBookMapper mapper;

    @Override
    public boolean create(PriceBookDTO priceBookDTO) {
        try {
            PriceBook priceBook = mapper.convertDTOToEntity(priceBookDTO);
            repository.save(priceBook);
            return true;
        } catch (Exception ex) {
            log.error("Error when create priceBook: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<PriceBookDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceBook> priceBooks = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(priceBooks);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all priceBooks:", e);
        }
        return false;
    }

    @Override
    public boolean update(PriceBookDTO priceBookDTO) {
        try {
            PriceBook priceBook = mapper.convertDTOToEntity(priceBookDTO);
            repository.save(priceBook);
            return true;
        } catch (Exception ex) {
            log.error("Error when update priceBook: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<PriceBookDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<PriceBook> priceBooks = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(priceBooks);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all priceBooks:", e);
        }
        return false;
    }

    @Override
    public PriceBookDTO findById(Long id) {
        Optional<PriceBook> priceBook = repository.findById(id);
        return priceBook.isPresent() ? mapper.convertEntityToDTO(priceBook.get()) : new PriceBookDTO();
    }

    @Override
    public List<PriceBookDTO> findAll() {
        List<PriceBook> priceBooks = repository.findAll();
        if (null != priceBooks && 0 < priceBooks.size()) {
            return priceBooks.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<PriceBookDTO> search(PriceBookDTO filter) {
        return null;
    }

    //end
}
