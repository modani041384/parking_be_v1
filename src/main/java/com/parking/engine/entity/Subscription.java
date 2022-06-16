package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "subscription")
@Data
@Getter
public class Subscription extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subscription_id")
    private String subscriptionId;//uuid

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "card_id", referencedColumnName = "card_id", nullable = false)
//    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
//    private Card card; --> TODO

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_type_id", referencedColumnName = "subscription_type_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private SubscriptionType subscriptionType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private Customer customer;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "actual_start_date")
    private Date actualStartDate;

    @Column(name = "actual_end_date")
    private Date actualEndDate;

    @Column(name = "last_extend_date")
    private Date lastExtendDate;

    @Column(name = "next_extend_date")
    private Date nextExtendDate;
}
