package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle_type")
@Data
@Getter
public class VehicleType extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehicle_type_id")
    private String vehicleTypeId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "pst_id", referencedColumnName = "pst_id", nullable = false)
//    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
//    private ParkingSlotType parkingSlotType; --> TODO

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Vehicle vehicle;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "vehicleType", cascade = CascadeType.ALL)
    private PriceBook priceBook;
}
