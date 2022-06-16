package com.parking.engine.mapper;

import com.parking.engine.entity.PriceBook;
import com.parking.engine.request.PriceBookDTO;
import org.springframework.stereotype.Service;

@Service
public class PriceBookMapper extends AbstractMapper<PriceBook, PriceBookDTO> {
    public PriceBookMapper() {
        super(PriceBook.class, PriceBookDTO.class);
    }
}
