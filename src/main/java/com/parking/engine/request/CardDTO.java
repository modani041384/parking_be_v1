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
public class CardDTO implements Serializable {
    @JsonIgnore
    private Long id;
    //@JsonProperty("card_id")
    private String cardId;//uuid
    private String cardCode;
    //@JsonProperty("identity_code")
    private String identityCode;
    private String name;
}
