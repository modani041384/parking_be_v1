package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card")
@Data
@Getter
public class Card extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_id")
    private String cardId;//uuid

    @Column(name = "card_code")
    private String cardCode;//uuid

    @Column(name = "identity_code")
    private String identityCode;

    @Column(name = "name")
    private String name;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
//    private DeviceStatus deviceStatus; --> TODO

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
    private Set<CardAssignment> cardAssignments = new HashSet<>();

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
//    private Subscription subscription; --> TODO

    //ParkingRecord --> ???

    @Column(name = "is_deleted")
    private String isDeleted;

}
