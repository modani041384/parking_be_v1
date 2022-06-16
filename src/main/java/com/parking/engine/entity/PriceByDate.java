package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "price_by_date")
@Data
@Getter
public class PriceByDate extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_by_date_id")
    private String priceByDateId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_type_id", referencedColumnName = "condition_type_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
    private ConditionType conditionType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "price_book_id", referencedColumnName = "price_book_id", nullable = false)
    private PriceBook priceBook;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "days_in_week")
    private String daysInWeek;
}
