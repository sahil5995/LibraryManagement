package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private DataService dataService;

    public List<BookEntity> getAllBooks() {
        List<BookEntity> list = dataService.getAllBooksFromDB();
        return list;
    }

    public List<UserBookEntity> getUserBooks(String username) {
        List<UserBookEntity> list = dataService.getUserBooksFromDB(username);
        return list;
    }

    public int borrowBook(String bookId) {
        BookEntity entity = dataService.findBookById(Long.valueOf(bookId));

        if(!dataService.isBorrowLimitFull(Constants.USERNAME)){
            if(!dataService.isBookAlreadyBorrowed(Constants.USERNAME,entity.getName())) {
                dataService.addBookToUser(entity.getName());
                entity.setQuantity(entity.getQuantity() - 1);
                dataService.saveBook(entity);
                return 1;
            }
            else {
                return 2;
            }
        }
        else{
            return 0;
        }

    }

}
