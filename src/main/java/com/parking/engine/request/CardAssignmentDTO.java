package com.parking.engine.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.parking.engine.entity.Parking;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class CardAssignmentDTO implements Serializable {
    private Long id;
    //@JsonIgnore
    private Parking parking;
    private String cardId;
}
