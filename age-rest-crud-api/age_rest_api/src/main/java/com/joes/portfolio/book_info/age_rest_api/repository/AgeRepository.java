package com.joes.portfolio.book_info.age_rest_api.repository;


import com.joes.portfolio.book_info.age_rest_api.model.Age;
import org.springframework.data.repository.CrudRepository;

public interface AgeRepository extends CrudRepository<Age, Long> {
    // all crudRepository methods
}
