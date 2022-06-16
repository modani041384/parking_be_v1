package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "price_by_hour")
@Data
@Getter
public class PriceByHour extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_by_hour_id")
    private String priceByHourId;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "offset")
    private Integer offset;

    @Column(name = "price_unit")
    private Integer priceUnit;

    @Column(name = "price")
    private Double price;

    @Column(name = "price_division")
    private String priceDivision;
}
