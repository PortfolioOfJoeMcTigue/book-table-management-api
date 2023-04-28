package com.joes.portfolio.book_info.book_rest_api.repository;


import com.joes.portfolio.book_info.book_rest_api.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
