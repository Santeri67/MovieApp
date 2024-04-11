package com.harjoitustyo.movieapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Merkitsee luokan JPA-entiteetiksi
@Table(name = "kayttajat")
public class User {

    @Id // Määrittää seuraavan kentän pääavaimen (primary key) sarakkeeksi
    @GeneratedValue(strategy = GenerationType.AUTO) // Automaattinen pääavaimen generointi
    private Long id;

    // Lisää muita ominaisuuksia, kuten käyttäjänimi ja salasana
    private String username;
    private String password;

    // Jätä pois tietoturvasyistä, kun käytät oikeita salasanoja
    private String roles; // Yksinkertainen roolien hallinta esimerkin vuoksi

    // Getterit ja setterit kaikille kentille
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
