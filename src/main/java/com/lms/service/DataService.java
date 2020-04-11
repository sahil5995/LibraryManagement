package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.repository.BookRepository;
import com.lms.repository.UserBookRepository;
import com.lms.utils.Constants;
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
        List<BookEntity> list = (List<BookEntity>) bookRepository.findAll();
        return list;
    }

    public List<UserBookEntity> getUserBooksFromDB(String username) {
        List<UserBookEntity> list = userBookRepo.findAllByUsername(username);
        return list;
    }

    public boolean isBorrowLimitFull(String username) {
        List<UserBookEntity> list = userBookRepo.findByUsernameAndStatus(username, Constants.BORROWED);
        return list.size() == 2;
    }

    public BookEntity findBookById(Long id) {
        BookEntity entity = bookRepository.findOne(id);
        return entity;
    }

    public void saveBook(BookEntity entity) {
        bookRepository.save(entity);
    }

    public void addBookToUser(String bookname) {
        UserBookEntity userBookEntity = new UserBookEntity(bookname);
        userBookEntity.setUsername(Constants.USERNAME);
        userBookEntity.setStatus(Constants.BORROWED);
        userBookRepo.save(userBookEntity);
    }

}
