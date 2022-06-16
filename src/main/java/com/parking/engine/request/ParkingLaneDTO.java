package com.parking.engine.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class ParkingLaneDTO implements Serializable {
    @JsonIgnore
    private Long id;
    //@JsonProperty("parking_lane_id")
    private String parkingLaneId;//uuid
    private String name;
}
