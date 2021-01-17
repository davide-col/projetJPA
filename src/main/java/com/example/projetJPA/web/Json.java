package com.example.projetJPA.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Json {
    private Long id;
    private String name;
    private String streetName;
    private int streetNumber;
    private String city;
    private String clientName;
    private String email;

    public Json (String name, String streetName, int streetNumber, String city, String clientName, String email){
        this.name = name;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.clientName = clientName;
        this.email = email;
    }
}