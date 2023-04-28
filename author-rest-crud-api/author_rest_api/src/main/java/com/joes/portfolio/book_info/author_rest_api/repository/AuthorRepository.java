package com.joes.portfolio.book_info.author_rest_api.repository;


import com.joes.portfolio.book_info.author_rest_api.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    // all crudRepository methods
}
