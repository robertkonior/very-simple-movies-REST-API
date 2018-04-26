package com.application.movies.mapper;

import com.application.movies.domain.Movie;
import com.application.movies.domain.MovieDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieMapperTestSuite {

    @Autowired
    private MovieMapper mapper;

    @Test
    public void testMapToMovie() {
        //Given
        MovieDto movieToMap = new MovieDto(1L, "example", "horror", "this is movie");
        Movie expectedMovie = new Movie(1L, "example", "horror", "this is movie");
        //When
        Movie mappedMovie = mapper.mapToMovie(movieToMap);
        //Then
        Assert.assertEquals(expectedMovie, mappedMovie);
    }

    @Test
    public void testMapToMovieDto() {
        //Given
        Movie movieToMap = new Movie(1L, "example", "horror", "this is movie");
        MovieDto expectedMovie = new MovieDto(1L, "example", "horror", "this is movie");
        //When
        MovieDto mappedMovie = mapper.mapToMovieDto(movieToMap);
        //Then
        Assert.assertEquals(expectedMovie, mappedMovie);
    }

    @Test
    public void testMapToMovieTitleDtoList() {
        //Given
        List<Movie> listToMap = Arrays.asList(
                new Movie(1L, "example", "horror", "this is movie"),
                new Movie(2L, "example2", "horror2", "this is movie2"));
        List<String> expectedList = Arrays.asList("Movie Id: 1  Title: example","Movie Id: 2  Title: example2");
        //When
        List<String> mappedList = mapper.mapToMovieTitleDtoList(listToMap);
        //Then
        Assert.assertEquals(expectedList, mappedList);
    }
}