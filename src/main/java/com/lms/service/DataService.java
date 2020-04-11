package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getAllBooksFromDB(){
        List<BookEntity> list = (List<BookEntity>) bookRepository.findAll();
        return list;
    }


}
