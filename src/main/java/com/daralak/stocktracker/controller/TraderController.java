package com.daralak.stocktracker.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import com.daralak.stocktracker.model.Trader;
import com.daralak.stocktracker.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TraderController {
    @Autowired
    private TraderRepository traderRepository;

    public TraderController(TraderRepository traderRepository) {
        super();
        this.traderRepository = traderRepository;
    }


    @GetMapping("/traders")
    Collection<Trader> traders(){
        return traderRepository.findAll();
    }

    //trader/2
    @GetMapping("/trader/{id}")
    ResponseEntity<?> getTrader(@PathVariable Long id){
        Optional<Trader> trader = traderRepository.findById(id);
        return trader.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/traders")
    ResponseEntity<Trader> createTrader(@Validated @RequestBody Trader trader) throws URISyntaxException{
        Trader result= traderRepository.save(trader);
        return ResponseEntity.created(new URI("/api/trader" + result.getId())).body(result);

    }


    @PutMapping("/trader/{id}")
    ResponseEntity<Trader> updateTrader(@Validated @RequestBody Trader trader){
        Trader result= traderRepository.save(trader);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/trader/{id}")
    ResponseEntity<?> deleteTrader(@PathVariable Long id){
        traderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
