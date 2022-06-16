package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parking_lane")
@Data
@Getter
public class ParkingLane extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parking_lane_id")
    private String parkingLaneId;//uuid

    @ManyToMany(mappedBy = "parkingLanes", cascade = CascadeType.ALL)
    private Set<Camera> cameras = new HashSet<>();

    @ManyToMany(mappedBy = "parkingLanes", cascade = CascadeType.ALL)
    private Set<MultifunctionGate> multifunctionGates = new HashSet<>();

    @ManyToMany(mappedBy = "parkingLanes", cascade = CascadeType.ALL)
    private Set<Parking> parking = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")//0: is active, 1: is deleted
    private String isDeleted;
}
