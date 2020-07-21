package com.daralak.stocktracker.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import com.daralak.stocktracker.model.Stock;
import com.daralak.stocktracker.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class StockController {
    @Autowired
    private StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        super();
        this.stockRepository = stockRepository;
    }


    @GetMapping("/stocks")
    Collection<Stock> stocks(){
        return stockRepository.findAll();
    }

    //stock/2
    @GetMapping("/stock/{id}")
    ResponseEntity<?> getStock(@PathVariable Long id){
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/stocks")
    ResponseEntity<Stock> createStock(@Validated @RequestBody Stock stock) throws URISyntaxException{
        Stock result= stockRepository.save(stock);
        return ResponseEntity.created(new URI("/api/stock" + result.getId())).body(result);

    }


    @PutMapping("/stock/{id}")
    ResponseEntity<Stock> updateStock(@Validated @RequestBody Stock stock){
        Stock result= stockRepository.save(stock);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/stock/{id}")
    ResponseEntity<?> deleteStock(@PathVariable Long id){
        stockRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
