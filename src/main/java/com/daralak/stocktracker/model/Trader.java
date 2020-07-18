package com.daralak.stocktracker.model;

import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="trader")
public class Trader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String  name;

    private String email;


}
