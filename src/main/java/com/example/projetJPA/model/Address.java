package com.example.projetJPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private int streetNumber;
    private String streetName;
//    @ManyToOne(cascade= CascadeType.PERSIST)
    private String city;

    public Address(){
        this.streetNumber = 0;
        this.streetName = "";
    }

    public Address(int streetNumber, String streetName, String city){
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
    }

    public Long getId(){
        return this.id;
    }

    public int getStreetNumber(){
        return this.streetNumber;
    }

    public String getStreetName(){
        return this.streetName;
    }

    public String getCity(){
        return this.city;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setStreetNumber(int streetNumber){
        this.streetNumber=streetNumber;
    }

    public void setStreetName(String streetName){
        this.streetName=streetName;
    }

    public void setCity(String city){
        this.city=city;
    }
}