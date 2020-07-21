package com.daralak.stocktracker.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import com.daralak.stocktracker.model.Sale;
import com.daralak.stocktracker.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository) {
        super();
        this.saleRepository = saleRepository;
    }


    @GetMapping("/sales")
    Collection<Sale> sales(){
        return saleRepository.findAll();
    }

    //sale/2
    @GetMapping("/sale/{id}")
    ResponseEntity<?> getSale(@PathVariable Long id){
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/sales")
    ResponseEntity<Sale> createSale(@Validated @RequestBody Sale sale) throws URISyntaxException{
        Sale result= saleRepository.save(sale);
        return ResponseEntity.created(new URI("/api/sale" + result.getId())).body(result);

    }


    @PutMapping("/sale/{id}")
    ResponseEntity<Sale> updateSale(@Validated @RequestBody Sale sale){
        Sale result= saleRepository.save(sale);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/sale/{id}")
    ResponseEntity<?> deleteSale(@PathVariable Long id){
        saleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
