package com.parking.engine.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagingDTO<T> {
    private Integer size;
    private Integer page;
    private String orderBy;
    private String sorted;
    private T filter;
}
