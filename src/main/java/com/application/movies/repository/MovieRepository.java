package com.application.movies.repository;

import com.application.movies.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Override
    List<Movie> findAll();

    @Override
    Optional<Movie> findById(Long Id);

    @Override
    Movie save(Movie movie);
}
