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
public class ParkingSlotTypeDTO implements Serializable {
    @JsonIgnore
    private Long id;
    //@JsonProperty("pst_id")
    private String pstId;//uuid
    //@JsonProperty("pst_code")
    private String pstCode;
    private String name;
    private String description;
    //@JsonProperty("is_default")
    private String isDefault;
}
