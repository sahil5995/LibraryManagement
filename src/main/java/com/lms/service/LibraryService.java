package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.utils.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LibraryService {

    @Autowired
    private DataService dataService;

    /**
     * This method is used to fetch all books for Library
     *
     * @return List
     */
    public List<BookEntity> getAllBooks() {
        return dataService.getAllBooksFromDB();
    }

    /**
     * The method is used to fetch all books for user
     *
     * @param username username
     * @return List of books
     */
    public List<UserBookEntity> getUserBooks(String username) {
        return dataService.getUserBooksFromDB(username);
    }

    /**
     * This method is used to issue a book to user
     *
     * @param bookId Id of book
     * @return status code
     */
    public int borrowBook(String bookId) {
        BookEntity bookEntity = dataService.findBookById(Long.valueOf(bookId));

        if (!dataService.isBorrowLimitFull(Properties.USERNAME)) {
            if (!dataService.isBookAlreadyBorrowed(Properties.USERNAME, bookEntity.getName())) {
                dataService.issueBookToUser(bookEntity.getName());
                dataService.updateQuantityForBook(bookEntity.getName(), -1);
                log.info("Book {} borrowed by user {}", bookEntity.getName(), Properties.USERNAME);
                return 1;
            } else {
                log.info("Book {} is already borrowed by user {}", bookEntity.getName(), Properties.USERNAME);
                return 2;
            }
        } else {
            log.info("Book borrowing limit is over for user {}", Properties.USERNAME);
            return 0;
        }
    }

    /**
     * This method is used to return the book borrowed by user
     *
     * @param bookname bookname
     */
    public void returnBook(String bookname) {
        dataService.updateQuantityForBook(bookname, 1);
        dataService.updateUserBookStatus(Properties.USERNAME, bookname);
        log.info("Book {} returned by user {}", bookname, Properties.USERNAME);
    }

}
