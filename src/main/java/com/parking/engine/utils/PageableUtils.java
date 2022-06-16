package com.parking.engine.utils;

import com.parking.engine.consts.PagingConstant;
import com.parking.engine.dto.PagingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PageableUtils {
    /**
     * @param list
     * @param pageable
     * @return
     */
    public static Page<?> toPage(List<?> list, Pageable pageable) {
        if (null == pageable || list == null || list.size() == 0) return null;
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        if (start > list.size())
            return new PageImpl<>(new ArrayList<>(), pageable, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    /**
     * @param list
     * @param request
     * @return
     */
    public static Page<?> toPage(List<?> list, PagingDTO<?> request) {
        int size = request.getSize() == null ? PagingConstant.size : request.getSize();
        int page = request.getPage() == null ? PagingConstant.page : request.getPage();
        Pageable pageable = PageRequest.of(page - 1, size);
        return toPage(list, pageable);
    }
}
