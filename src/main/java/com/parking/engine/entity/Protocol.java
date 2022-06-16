package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "protocol")
@Data
@Getter
public class Protocol extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "camera_id", referencedColumnName = "camera_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Camera camera;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "multifunction_gate_id", referencedColumnName = "mfg_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private MultifunctionGate multifunctionGate;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_default")
    private String isDefault;
}
