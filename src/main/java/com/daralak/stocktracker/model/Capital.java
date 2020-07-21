package com.daralak.stocktracker.model;


import java.time.Instant;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="capital")
public class Capital {
    @Id
    @GeneratedValue
    private Long id;
    private float value;
    private Instant addedDate;
    private String description;
    private Long refId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    // many capitals can be associated to one trader
    private Trader trader;
}
