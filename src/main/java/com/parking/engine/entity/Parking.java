package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parking")
@Data
@Getter
public class Parking extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parking_id")
    private String parkingId;//uuid

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "parking_parking_lane",
            joinColumns = {
                    @JoinColumn(name = "parking_id", referencedColumnName = "parking_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "parking_lane_id", referencedColumnName = "parking_lane_id")
            }
    )
    private Set<ParkingLane> parkingLanes = new HashSet<>();

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parking")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parking", cascade = CascadeType.ALL)
    private Set<SlotTypeConfig> slotTypeConfigs = new HashSet<>();

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parking")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parking", cascade = CascadeType.ALL)
    private Set<CardAssignment> cardAssignments = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parking")
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parking", cascade = CascadeType.ALL)
//    private Set<PriceBookAssignment> priceBookAssignments = new HashSet<>();//????

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parking", cascade = CascadeType.ALL)
    private Set<PriceBook> priceBook = new HashSet<>();//Many to many not one to many

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "area")
    private String area;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "is_deleted")//0: is active, 1: is deleted
    private String isDeleted;
}
