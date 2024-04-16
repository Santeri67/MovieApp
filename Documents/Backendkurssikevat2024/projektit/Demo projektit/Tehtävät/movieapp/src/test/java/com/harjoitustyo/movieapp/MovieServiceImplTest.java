package com.harjoitustyo.movieapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.harjoitustyo.movieapp.domain.Movie;
import com.harjoitustyo.movieapp.repository.MovieRepository;
import com.harjoitustyo.movieapp.service.MovieServiceImpl;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    void whenSaveNewMovie_shouldReturnMovie() {
    Movie movie = new Movie();
    movie.setTitle("Test Movie");
    movie.setGenre("Test Genre");

    when(movieRepository.save(movie)).thenReturn(movie);  // Use direct instance

    Movie created = movieService.saveMovie(movie);

    assertNotNull(created, "The saved movie should not be null");
    assertEquals(movie.getTitle(), created.getTitle(), "The movie titles should match");

    verify(movieRepository).save(movie);
}
    @Test
    void whenSaveMovie_withNullMovie_shouldThrowException() {
    // Setup the expectation for the exception
    assertThrows(IllegalArgumentException.class, () -> {
        movieService.saveMovie(null);
    }, "Should throw IllegalArgumentException for null movie");

    // Ensure no interaction with the repository
    verifyNoInteractions(movieRepository);
}

    @Test
    void whenGetAllMovies_shouldReturnMovieList() {
        // Setup your test data and expectations
        // Call movieService.getAllMovies() and assert the results
    }

    // Write more tests for other methods and scenarios...
}
