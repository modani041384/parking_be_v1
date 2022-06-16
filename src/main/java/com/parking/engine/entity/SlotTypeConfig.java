package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "slot_type_config")
@Data
@Getter
public class SlotTypeConfig extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_id", referencedColumnName = "parking_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Parking parking;

    @Column(name = "slot_type_id")
    private String slotTypeId;//uuid

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "used")
    private Integer used;
}
