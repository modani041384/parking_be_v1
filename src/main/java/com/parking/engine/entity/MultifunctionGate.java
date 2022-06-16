package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "multifunction_gate")
@Data
@Getter
public class MultifunctionGate extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mfg_id")
    private String mfgId;//uuid

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "multifunction_gate_parking_lane",
            joinColumns = {
                    @JoinColumn(name = "mfg_id", referencedColumnName = "mfg_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "parking_lane_id", referencedColumnName = "parking_lane_id")
            }
    )
    private Set<ParkingLane> parkingLanes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "multifunctionGate", cascade = CascadeType.ALL)
    private Set<Protocol> protocols = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "multifunctionGate", cascade = CascadeType.ALL)
    private Set<DeviceStatus> deviceStatus = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "oem")
    private String oem;//producer

    @Column(name = "protocol")
    private String protocol;

    @Column(name = "position")
    private String position;

    @Column(name = "is_deleted")
    private String isDeleted;
}
