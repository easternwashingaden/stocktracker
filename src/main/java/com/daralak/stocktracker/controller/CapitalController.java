package com.daralak.stocktracker.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import com.daralak.stocktracker.model.Capital;
import com.daralak.stocktracker.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CapitalController {
    @Autowired
    private CapitalRepository capitalRepository;

    public CapitalController(CapitalRepository capitalRepository) {
        super();
        this.capitalRepository = capitalRepository;
    }


    @GetMapping("/capitals")
    Collection<Capital> capitals(){
        return capitalRepository.findAll();
    }

    //capital/2
    @GetMapping("/capital/{id}")
    ResponseEntity<?> getCapital(@PathVariable Long id){
        Optional<Capital> capital = capitalRepository.findById(id);
        return capital.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/capitals")
    ResponseEntity<Capital> createCapital(@Validated @RequestBody Capital capital) throws URISyntaxException{
        Capital result= capitalRepository.save(capital);
        return ResponseEntity.created(new URI("/api/capital" + result.getId())).body(result);

    }

    @PutMapping("/capital/{id}")
    ResponseEntity<Capital> updateCapital(@Validated @RequestBody Capital capital){
        Capital result= capitalRepository.save(capital);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/capital/{id}")
    ResponseEntity<?> deleteCapital(@PathVariable Long id){
        capitalRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
