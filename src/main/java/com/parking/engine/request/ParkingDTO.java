package com.parking.engine.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.engine.entity.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class ParkingDTO implements Serializable {
    @JsonIgnore
    private Long id;
    private String parkingId;//uuid
    //@JsonProperty("slot_type_configs")
    private List<SlotTypeConfig> slotTypeConfigs;
    //@JsonProperty("card_assignment")
    private List<CardAssignment> cardAssignments;
    //@JsonProperty("price_booking_assignment")
    private List<PriceBookAssignment> priceBookAssignments;
    //@JsonProperty("price_books")
    private List<PriceBook> priceBooks;
    //@JsonProperty("parking_lane")
    private List<ParkingLane> parkingLanes;
    private String name;
    private String address;
    private String area;
    private Integer quantity;
}
