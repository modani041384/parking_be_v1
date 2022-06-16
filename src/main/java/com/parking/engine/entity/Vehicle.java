package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle")
@Data
@Getter
public class Vehicle extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehicle_id")
    private String vehicleId;//uuid

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "vehicle", cascade = CascadeType.ALL)
    private VehicleType vehicleType;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Subscription subscription;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "color")
    private String color;

    @Column(name = "model")
    private String model;

}
