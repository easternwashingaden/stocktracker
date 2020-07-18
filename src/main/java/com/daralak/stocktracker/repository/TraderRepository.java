package com.daralak.stocktracker.repository;

import com.daralak.stocktracker.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository  extends JpaRepository<Trader, Long>{
    Trader findByName(String name);
}