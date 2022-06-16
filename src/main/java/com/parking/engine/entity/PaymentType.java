package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment_type")
@Data
@Getter
public class PaymentType extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_type_id")
    private String paymentTypeId;//uuid

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "paymentType", cascade = CascadeType.ALL)
    private SubscriptionType subscriptionType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_required_customer")
    private String isRequiredCustomer;

    @Column(name = "is_default")
    private String isDefault;
}
