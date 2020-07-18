package com.daralak.stocktracker.repository;

import com.daralak.stocktracker.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository  extends JpaRepository<Stock, Long>{
    Stock findByTicker(String ticker);
}