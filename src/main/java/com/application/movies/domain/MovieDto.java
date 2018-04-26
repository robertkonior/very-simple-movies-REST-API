package com.application.movies.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class MovieDto {

    private Long id;
    private String title;
    private String genre;
    private String description;
}
