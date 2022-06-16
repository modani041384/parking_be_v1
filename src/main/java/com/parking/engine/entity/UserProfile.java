package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_profile")
@Data
@Getter
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId; //uuid

    @Column(name = "personal_id")
    private String personalId;//CMND or CCCD

    @Column(name = "manager_id")
    private String managerId;//id user of manager

    @Column(name = "management")
    private String management;//list member take care

    @Column(name = "parking_assignment")
    private String parkingAssignment;//list parking take care
}
