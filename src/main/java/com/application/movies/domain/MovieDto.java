package com.application.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private String genre;
    private String description;
}
