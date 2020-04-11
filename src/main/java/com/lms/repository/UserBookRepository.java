package com.lms.repository;

import com.lms.entity.UserBookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookRepository extends CrudRepository<UserBookEntity, Long> {

    List<UserBookEntity> findByUsernameAndStatus(String username,String status);
    List<UserBookEntity> findAllByUsername(String username);
    UserBookEntity findByUsernameAndBooknameAndStatus(String username,String bookname, String status);
}