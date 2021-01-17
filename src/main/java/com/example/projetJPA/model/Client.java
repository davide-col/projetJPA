package com.example.projetJPA.model;

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
@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

    public Client(String name, String email){
        this.name = name;
        this.email = email;
    }

//    public Long getId(){
//        return this.id;
//    }
//
//    public String getName(){
//        return this.name;
//    }
//
//    public String getEmail(){
//        return this.email;
//    }
//
//    public void setId(Long id){
//        this.id=id;
//    }
//
//    public void setName(String name){
//        this.name=name;
//    }
//
//    public void setEmail(String email){
//        this.email=email;
//    }
}