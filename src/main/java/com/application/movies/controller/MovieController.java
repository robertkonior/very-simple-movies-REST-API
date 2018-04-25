package com.application.movies.controller;

import com.application.movies.domain.MovieDto;
import com.application.movies.mapper.MovieMapper;
import com.application.movies.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    DbService service;
    @Autowired
    MovieMapper movieMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public List<String> getMovies() {
        return movieMapper.mapToMovieTitleDtoList(service.getAllMovies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{movieId}")
    public MovieDto getMovie(@PathVariable Long movieId) throws MovieNotFoundException {
        return movieMapper.mapToMovieDto(service.getMovie(movieId).orElseThrow(MovieNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movies", consumes = APPLICATION_JSON_VALUE)
    public void addMovie(@RequestBody MovieDto movieDto) {
        service.addMovie(movieMapper.mapToMovie(movieDto));
    }
}
