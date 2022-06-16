package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "condition_type")
@Data
@Getter
public class ConditionType extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "condition_type_id")
    private String conditionTypeId;//uuid

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "conditionType", cascade = CascadeType.ALL)
    private PriceByDate priceByDate;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_default")
    private String isDefault;
}
