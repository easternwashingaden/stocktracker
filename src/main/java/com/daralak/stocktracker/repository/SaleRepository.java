package com.daralak.stocktracker.repository;

import com.daralak.stocktracker.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository  extends JpaRepository<Sale, Long>{
    Sale findByTicker(String ticker);
}