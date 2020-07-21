package com.daralak.stocktracker.repository;

import com.daralak.stocktracker.model.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapitalRepository extends JpaRepository<Capital, Long>{
    Capital findByDescription(String description);
}
