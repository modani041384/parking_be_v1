package com.parking.engine.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class UserRoleDTO {
    @JsonIgnore
    private Long id;
    private String userId;//uuid
    private Long roleId;
}
