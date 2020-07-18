package com.daralak.stocktracker.model;


import java.time.Instant;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="sale")
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    private String ticker;
    private int share;
    private float price;
    private Instant purchasedDate;
    private float soldPrice;
    private Instant soldDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    // many sales can be associated to one trader
    private Trader trader;
}
