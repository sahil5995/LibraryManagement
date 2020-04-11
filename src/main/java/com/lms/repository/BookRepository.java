package com.lms.repository;

import com.lms.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {

    List<BookEntity> findByQuantityGreaterThan(int minimum);

}