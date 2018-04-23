package com.application.movies.service;

import com.application.movies.domain.Movie;
import com.application.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private MovieRepository repository;

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Optional<Movie> getMovie(final Long id) {
        return repository.findById(id);
    }

    public Movie addMovie(final Movie movie) {
        return repository.save(movie);
    }
}
