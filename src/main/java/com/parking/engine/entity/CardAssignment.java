package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card_assignment")
@Data
@Getter
public class CardAssignment extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_assignment_id")
    private String cardAssignmentId;//uuid

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id", referencedColumnName = "parking_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Parking parking;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "card_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Card card;
}
