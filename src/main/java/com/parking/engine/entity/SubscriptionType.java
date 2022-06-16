package com.parking.engine.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subscription_type")
@Data
public class SubscriptionType extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subscription_type_id")
    private String subscriptionTypeId;//uuid

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "subscriptionType", cascade = CascadeType.ALL)
    private Subscription subscription;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "subscriptionType", cascade = CascadeType.ALL)
    private PriceBook priceBook;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id", referencedColumnName = "payment_type_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private PaymentType paymentType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_duration_unit_id", referencedColumnName = "payment_duration_unit_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private PaymentDurationUnit paymentDurationUnit;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "payment_duration")
    private Integer paymentDuration;

    @Column(name = "is_default")
    private String isDefault;
}
