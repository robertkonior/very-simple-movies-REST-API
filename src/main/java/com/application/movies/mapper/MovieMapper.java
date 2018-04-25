package com.application.movies.mapper;

import com.application.movies.domain.Movie;
import com.application.movies.domain.MovieDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    public Movie mapToMovie (final MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getGenre(),
                movieDto.getDescription()
        );
    }

    public MovieDto mapToMovieDto(final Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getDescription()
        );
    }

    public List<String> mapToMovieTitleDtoList(final List<Movie> movieList) {
        return movieList.stream()
                .map(m-> "Movie Id: " + m.getId() + "  Title: " + m.getTitle())
                .collect(Collectors.toList());
    }
}
