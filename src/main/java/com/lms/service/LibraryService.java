package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.utils.Properties;
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

        if (!dataService.isBorrowLimitFull(Properties.USERNAME)) {
            if (!dataService.isBookAlreadyBorrowed(Properties.USERNAME, entity.getName())) {
                dataService.issueBookToUser(entity.getName());
                dataService.updateQuantityForBook(entity.getName(),-1);
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    public void returnBook(String bookname) {
        dataService.updateQuantityForBook(bookname,1);
        dataService.updateUserBookStatus(Properties.USERNAME, bookname);
    }

}
