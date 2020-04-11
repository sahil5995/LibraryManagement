package com.lms.service;

import com.lms.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private DataService dataService;

    public List<BookEntity> getAllBooks(){
        List<BookEntity> list = dataService.getAllBooksFromDB();
        return list;
    }


}
