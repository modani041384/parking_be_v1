package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "price_book")
@Data
@Getter
public class PriceBook extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_book_id")
    private String priceBookId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id", referencedColumnName = "vehicle_type_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private VehicleType vehicleType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_type_id", referencedColumnName = "subscription_type_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private SubscriptionType subscriptionType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_id", referencedColumnName = "parking_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Parking parking;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priceBook", cascade = CascadeType.ALL)
    private Set<PriceByDate> priceByDates = new HashSet<>();
}
