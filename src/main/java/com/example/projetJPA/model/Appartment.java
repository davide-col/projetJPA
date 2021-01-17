package com.example.projetJPA.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "appartments")
public class Appartment {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Address address;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Client client;

    public Appartment(){
        this.name = "";
        this.address = new Address();
        this.client = new Client();
    }

    public Appartment(String name, Address address, Client client){
        this.name = name;
        this.address = address;
        this.client = client;
    }


//    @ManyToOne(cascade=CascadeType.PERSIST)
//    private Set<Client> clients;

//    public Long getId(){
//        return this.id;
//    }
//
//    public String getName(){
//        return this.name;
//    }
//
//    public Address getAddress(){
//        return this.address;
//    }

//    public Client getClient(){
//        return this.client;
//    }
//
//
//    public void setId(Long id){
//        this.id=id;
//    }
//
//    public void setName(String name){
//        this.name=name;
//    }
//
//    public void setAddress(Address address){
//        this.address=address;
//    }

//    public void setClient(Client client){
//        this.client=client;
//    }

}