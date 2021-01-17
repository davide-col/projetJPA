package com.example.projetJPA.web;

import com.example.projetJPA.model.Appartment;
import com.example.projetJPA.model.Client;
import com.example.projetJPA.model.Address;
import com.example.projetJPA.model.AppartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//import org.json.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class AppartmentController {

    private final Logger log = LoggerFactory.getLogger(AppartmentController.class);
    private AppartmentRepository appartmentRepository;

    public AppartmentController(AppartmentRepository appartmentRepository) {
        this.appartmentRepository = appartmentRepository;
    }

    @GetMapping("/appartments")
    Collection<Appartment> appartments() {
        return appartmentRepository.findAll();
    }

    @GetMapping("/appartment/{id}")
    ResponseEntity<?> getAppartment(@PathVariable Long id) {
        Optional<Appartment> appartment = appartmentRepository.findById(id);
        return appartment.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/appartment")
    ResponseEntity<Appartment> createAppartment(@Valid @RequestBody Json json) throws URISyntaxException {
        System.out.println(json);
        int i = json.getStreetNumber();
        System.out.println("int is : " + i);
        Address a = new Address(i,json.getStreetName(),json.getCity());
        Client c = new Client(json.getClientName(),json.getEmail());
        Appartment appartment = new Appartment(json.getName(),a,c);
        System.out.println(appartment);
        log.info("Request to create appartment: {}", appartment);
        Appartment result = appartmentRepository.save(appartment);
        return ResponseEntity.created(new URI("/api/appartment/" + result.getId()))
                .body(result);
    }

    @PutMapping("/appartment/{id}")
    ResponseEntity<Appartment> updateAppartment(@Valid @RequestBody Json json) {
        Long appId = appartmentRepository.findByName(json.getName()).getId();
        Appartment app = new Appartment();
        app.setName(json.getName());
        app.getAddress().setStreetName(json.getStreetName());
        app.getAddress().setStreetNumber(json.getStreetNumber());
        app.getAddress().setCity(json.getCity());
        app.getClient().setName(json.getClientName());
        app.getClient().setEmail(json.getEmail());
        log.info("Request to update appartment: {}", app);
        Appartment result = appartmentRepository.save(app);
        deleteAppartment(appId);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/appartment/{id}")
    public ResponseEntity<?> deleteAppartment(@PathVariable Long id) {
        log.info("Request to delete appartment: {}", id);
        appartmentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}