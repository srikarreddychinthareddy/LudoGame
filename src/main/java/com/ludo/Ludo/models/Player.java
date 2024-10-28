package com.ludo.Ludo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Entity
@Getter
@Setter

public class Player extends User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    public Player(String username,String password,SimpleGrantedAuthority simpleGrantedAuthority){
        super(username,password, Collections.singletonList(simpleGrantedAuthority));
    }

}
