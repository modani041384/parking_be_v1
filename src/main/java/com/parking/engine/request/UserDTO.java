package com.parking.engine.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class UserDTO {
    @JsonIgnore
    private Long id;
    private Long roleId;
    private String userId;
    private String userNo;
    private String credential;
    private String firstName;
    private String LastName;
    private String email;
    private String phone;
    private String gender;
    private String jobTitle;
    private String birthday;
    private String title;
    private String address;
    private String active;
    private String createdBy;
    private String modifiedBy;
    private String userName;
    private String password;
}
