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
public class CameraDTO implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonProperty("uuid")
    private String cameraId;//uuid
    @JsonProperty("q")
    private String name;
    //@JsonProperty("server_name")
    private String serverName;
    //@JsonProperty("user_name")
    private String userName;
    private String password;
    //@JsonProperty("url_template")
    private String urlTemplate;
    private String protocol;
    private String idOfCamera;
    //@JsonProperty("stream_id")
    private String streamId;
    private String oem;
    private String position;
    private String isDeleted;
    private String parkingLaneName;

    //end
}
