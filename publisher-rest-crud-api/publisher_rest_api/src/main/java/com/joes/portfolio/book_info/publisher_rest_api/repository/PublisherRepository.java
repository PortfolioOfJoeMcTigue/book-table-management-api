package com.joes.portfolio.book_info.publisher_rest_api.repository;

import com.joes.portfolio.book_info.publisher_rest_api.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    // all crudRepository methods
}
