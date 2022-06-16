package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment_duration_unit")
@Data
@Getter
public class PaymentDurationUnit extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_duration_unit_id")
    private String paymentDurationUnitId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "paymentDurationUnit", cascade = CascadeType.ALL)
    private SubscriptionType subscriptionType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_default")
    private String isDefault;
}
