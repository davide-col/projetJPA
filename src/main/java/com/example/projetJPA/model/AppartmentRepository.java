package com.example.projetJPA.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppartmentRepository extends JpaRepository<Appartment, Long> {
    Appartment findByName(String name);
}
