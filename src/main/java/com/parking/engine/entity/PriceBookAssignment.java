package com.parking.engine.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "price_book_assignment")
@Data
@Getter
public class PriceBookAssignment extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price_book_id")
    private String priceBookId;//uuid

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "parking_id", referencedColumnName = "parking_id", nullable = false)
//    //@OnDelete(action = OnDeleteAction.CASCADE) --> read more
//    private Parking parking;

}
