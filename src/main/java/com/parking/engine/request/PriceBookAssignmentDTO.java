package com.parking.engine.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parking.engine.entity.Parking;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class PriceBookAssignmentDTO {
    private Long id;
    private Parking parking;
    private String priceBookId;//uuid
}
