package com.application.movies.controller;

import com.application.movies.domain.Movie;
import com.application.movies.domain.MovieDto;
import com.application.movies.mapper.MovieMapper;
import com.application.movies.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private MovieMapper mapper;

    @Test
    public void shouldFetchMovies() throws Exception {
        //Given
        List<Movie> moviesList = Arrays.asList(
                new Movie(1L, "example", "horror", "this is movie"),
                new Movie(2L, "example2", "horror", "this is movie"));
        List<String> movieListDto = Arrays.asList("Movie Id: 1  Title: example", "Movie Id: 2  Title: example2");

        when(service.getAllMovies()).thenReturn(moviesList);
        when(mapper.mapToMovieTitleDtoList(moviesList)).thenReturn(movieListDto);
        //When&Then
        mockMvc.perform(get("/api/movies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(service, times(1)).getAllMovies();
    }

    @Test
    public void shouldFetchMovie() throws Exception {
        //Given
        Movie movie = new Movie(1L, "example", "horror", "this is movie");
        MovieDto movieDto = new MovieDto(1L, "example", "horror", "this is movie");

        when(service.getMovie(1L)).thenReturn(Optional.ofNullable(movie));
        when(mapper.mapToMovieDto(movie)).thenReturn(movieDto);
        //When&Then
        mockMvc.perform(get("/api/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("example")))
                .andExpect(jsonPath("$.genre", is("horror")))
                .andExpect(jsonPath("$.description", is("this is movie")));
        verify(service, times(1)).getMovie(1L);
    }

    @Test
    public void shouldThrowMovieNotFoundException() throws Exception {
        //Given
        when(service.getMovie(anyLong())).thenReturn(Optional.empty());
        //When&Then
        mockMvc.perform(get("/api/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
        verify(service, times(0)).getMovie(1L);
    }

    @Test
    public void shouldAddMovie() throws Exception {
        //Given
        Movie movie = new Movie(1L, "example", "horror", "this is movie");
        MovieDto movieDto = new MovieDto(1L, "example", "horror", "this is movie");

        when(mapper.mapToMovie(any(MovieDto.class))).thenReturn(movie);
        when(service.addMovie(any(Movie.class))).thenReturn(movie);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(movieDto);
        //When&Then
        mockMvc.perform(post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(service, times(1)).addMovie(movie);
    }
}