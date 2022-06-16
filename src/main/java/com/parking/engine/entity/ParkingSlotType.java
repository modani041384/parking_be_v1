package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "parking_slot_type")
@Data
@Getter
public class ParkingSlotType extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pst_id")
    private String pstId;//uuid

    @Column(name = "pst_code")
    private String pstCode;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "parkingSlotType", cascade = CascadeType.ALL)
//    private VehicleType vehicleType; --> TODO

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_default")
    private String isDefault;

    @Column(name = "is_deleted")//0: is active, 1: is deleted
    private String isDeleted;
}
