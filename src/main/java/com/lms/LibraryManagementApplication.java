package com.lms;


import com.lms.entity.BookEntity;
import com.lms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }


    /**
     * This method is inserting the dummy data for Library
     *
     * @param strings args
     */
    @Override
    public void run(String... strings) {
        bookRepository.save(new BookEntity("Java", 1));
        bookRepository.save(new BookEntity("Python", 6));
        bookRepository.save(new BookEntity("Perl", 2));
        bookRepository.save(new BookEntity("PHP", 8));
    }
}