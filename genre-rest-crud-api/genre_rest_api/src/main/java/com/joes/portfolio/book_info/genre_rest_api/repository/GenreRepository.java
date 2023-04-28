package com.joes.portfolio.book_info.genre_rest_api.repository;

import com.joes.portfolio.book_info.genre_rest_api.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    // all crudRepository methods.
}
