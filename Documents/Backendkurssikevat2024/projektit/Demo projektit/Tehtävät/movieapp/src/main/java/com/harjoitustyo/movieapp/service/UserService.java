package com.harjoitustyo.movieapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.domain.User;
import com.harjoitustyo.movieapp.repository.MovieRepository;
import com.harjoitustyo.movieapp.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MovieRepository movieRepository;


    @Autowired // Lisää tämä, jos konstruktori-injektointi ei ole vielä määritelty
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.movieRepository = movieRepository; // Lisää tämä
    }

    public User registerNewUserAccount(User user) {
        // Tarkista, ettei samalla käyttäjänimellä ole jo luotu tiliä
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already in use");
            // Heitä poikkeus tai käsittele tilanne, jos käyttäjänimi on jo käytössä
        }
        // Hashaa salasana ennen tallennusta
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER,ROLE_ADMIN"); // Aseta oletusrooli
        return userRepository.save(user);
}
    public void addFavoriteMovie(User user, Long movieId) {
        Movie movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new RuntimeException("Movie not found"));
            user.getFavoriteMovies().add(movie);
            userRepository.save(user);
}

public void removeFavoriteMovie(User user, Long movieId) {
    Movie movie = user.getFavoriteMovies()
        .stream()
        .filter(m -> m.getId().equals(movieId))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Favorite not found"));
    user.getFavoriteMovies().remove(movie);
    userRepository.save(user);
}


    // Voit lisätä täällä muita käyttäjään liittyviä palveluita
}