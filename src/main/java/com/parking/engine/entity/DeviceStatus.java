package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "device_status")
@Data
@Getter
public class DeviceStatus extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_status_id")
    private String deviceStatusId;//uuid

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "camera_id", referencedColumnName = "camera_id", nullable = false)
//    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
//    private Camera camera; --> TODO

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "multifunction_gate_id", referencedColumnName = "mfg_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private MultifunctionGate multifunctionGate;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "card_id", referencedColumnName = "card_id", nullable = false)
//    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
//    private Card card; --> TODO

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_default")
    private String isDefault;

    @Column(name = "is_deleted")
    private String isDeleted;
}
