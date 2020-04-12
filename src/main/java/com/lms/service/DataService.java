package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.repository.BookRepository;
import com.lms.repository.UserBookRepository;
import com.lms.utils.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DataService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserBookRepository userBookRepo;

    /**
     * This method returns all the books with quantity more than zero
     *
     * @return List
     */
    public List<BookEntity> getAllBooksFromDB() {
        List<BookEntity> list = bookRepository.findByQuantityGreaterThan(0);
        log.info("Returning list of {} books", list.size());
        return list;
    }

    /**
     * This method returns all the books borrowed by user
     *
     * @param username username
     * @return List
     */
    public List<UserBookEntity> getUserBooksFromDB(String username) {
        List<UserBookEntity> list = userBookRepo.findAllByUsernameAndStatus(username, Properties.BORROWED);
        log.info("Returning {} user books", list.size());
        return list;
    }


    /**
     * This method checks is the borrowed limit for user if full or not
     *
     * @param username username
     * @return List
     */
    public boolean isBorrowLimitFull(String username) {
        List<UserBookEntity> list = userBookRepo.findByUsernameAndStatus(username, Properties.BORROWED);
        return list.size() == 2;
    }

    /**
     * This method checks if the book is already borrowed by user or not
     *
     * @param username username
     * @param bookname bookname
     * @return boolean
     */
    public boolean isBookAlreadyBorrowed(String username, String bookname) {
        UserBookEntity en = userBookRepo.findByUsernameAndBooknameAndStatus(username, bookname, Properties.BORROWED);
        return (null != en);
    }


    /**
     * Returns a book by book id
     *
     * @param id book id
     * @return BookEntity Object
     */
    public BookEntity findBookById(Long id) {
        BookEntity entity = bookRepository.findOne(id);
        return entity;
    }

    /**
     * This method is used to issue a book to user
     *
     * @param bookname bookname
     */
    public void issueBookToUser(String bookname) {
        UserBookEntity userBookEntity = userBookRepo.findByUsernameAndBookname(Properties.USERNAME, bookname);
        if (userBookEntity == null) {
            userBookEntity = new UserBookEntity(bookname);
        }
        userBookEntity.setUsername(Properties.USERNAME);
        userBookEntity.setStatus(Properties.BORROWED);
        userBookRepo.save(userBookEntity);
        log.info("Book {} issued to user", bookname);
    }

    /**
     * This method is used to update the quantity of book in library in case of book
     * issued or returned
     *
     * @param bookname bookname
     * @param quantity quantity to update
     */
    public void updateQuantityForBook(String bookname, int quantity) {
        BookEntity entity = bookRepository.findByName(bookname);
        entity.setQuantity(entity.getQuantity() + quantity);
        bookRepository.save(entity);
        log.info("Quantity of {} book updated", bookname);
    }

    /**
     * This method is used to update the book status to Returned after it is returned by user
     *
     * @param username username
     * @param bookname bookname
     */
    public void updateUserBookStatus(String username, String bookname) {
        UserBookEntity ubEntity = userBookRepo.findByUsernameAndBookname(username, bookname);
        ubEntity.setStatus(Properties.RETURNED);
        userBookRepo.save(ubEntity);
        log.info("Status updated to {} for book {}", Properties.RETURNED, bookname);
    }

}
