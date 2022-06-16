package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "camera")
@Data
@Getter
public class Camera extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "camera_id")
    private String cameraId;//uuid

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "camera_parking_lane",
            joinColumns = {
                    @JoinColumn(name = "camera_id", referencedColumnName = "camera_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "parking_lane_id", referencedColumnName = "parking_lane_id")
            }
    )
    private Set<ParkingLane> parkingLanes = new HashSet<>();

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "camera", cascade = CascadeType.ALL)
//    private DeviceStatus deviceStatus; --> TODO

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "camera", cascade = CascadeType.ALL)
    private Set<Protocol> protocols = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "url_template")
    private String urlTemplate;

    @Column(name = "protocol")
    private String protocol;

    //cameraId = 0, 1 --> don't understand
    @Column(name = "id_of_camera")
    private String idOfCamera;

    //streamId = 0, 1 --> don't understand
    @Column(name = "stream_id")
    private String streamId;

    @Column(name = "oem")
    private String oem;//producer

    @Column(name = "position")
    private String position;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Transient
    private String parkingLaneName;

    //end
}
