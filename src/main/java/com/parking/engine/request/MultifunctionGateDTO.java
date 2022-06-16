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
public class MultifunctionGateDTO implements Serializable {
    @JsonIgnore
    private Long id;
    //@JsonProperty("mfg_id")
    private String mfgId;//uuid
    private String name;
    private String oem;
    private String protocol;
}
