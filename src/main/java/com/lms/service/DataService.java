package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.repository.BookRepository;
import com.lms.repository.UserBookRepository;
import com.lms.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserBookRepository userBookRepo;

    public List<BookEntity> getAllBooksFromDB() {
        List<BookEntity> list = bookRepository.findByQuantityGreaterThan(0);
        return list;
    }

    public List<UserBookEntity> getUserBooksFromDB(String username) {
        List<UserBookEntity> list = userBookRepo.findAllByUsernameAndStatus(username, Properties.BORROWED);
        return list;
    }

    public boolean isBorrowLimitFull(String username) {
        List<UserBookEntity> list = userBookRepo.findByUsernameAndStatus(username, Properties.BORROWED);
        return list.size() == 2;
    }

    public boolean isBookAlreadyBorrowed(String username, String bookname) {
        UserBookEntity en = userBookRepo.findByUsernameAndBooknameAndStatus(username, bookname, Properties.BORROWED);
        return (null != en);
    }


    public BookEntity findBookById(Long id) {
        BookEntity entity = bookRepository.findOne(id);
        return entity;
    }

    public void issueBookToUser(String bookname) {

        UserBookEntity userBookEntity = userBookRepo.findByUsernameAndBookname(Properties.USERNAME, bookname);
        if (userBookEntity == null) {
            userBookEntity = new UserBookEntity(bookname);
        }
        userBookEntity.setUsername(Properties.USERNAME);
        userBookEntity.setStatus(Properties.BORROWED);
        userBookRepo.save(userBookEntity);
    }

    public void updateQuantityForBook(String bookname, int quantity) {
        BookEntity entity = bookRepository.findByName(bookname);
        entity.setQuantity(entity.getQuantity() + quantity);
        bookRepository.save(entity);
    }

    public void updateUserBookStatus(String username, String bookname) {
        UserBookEntity ubEntity = userBookRepo.findByUsernameAndBookname(username, bookname);
        ubEntity.setStatus(Properties.RETURNED);
        userBookRepo.save(ubEntity);
    }

}
