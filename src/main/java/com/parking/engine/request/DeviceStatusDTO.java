package com.parking.engine.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parking.engine.entity.MultifunctionGate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class DeviceStatusDTO implements Serializable {
    private Long id;
    private String deviceStatusId;
    private MultifunctionGate multifunctionGate;
    private String name;
    private String description;
    private String isDefault;
    private String isDeleted;
}
