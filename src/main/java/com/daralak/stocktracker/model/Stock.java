package com.daralak.stocktracker.model;


import java.time.Instant;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private String ticker;
    private int share;
    private float price;
    private Instant purchasedDate;
//    private float currentPrice;

    @ManyToOne(cascade = CascadeType.PERSIST)
    // many stocks can be associated to one trader
    private Trader trader;
}
