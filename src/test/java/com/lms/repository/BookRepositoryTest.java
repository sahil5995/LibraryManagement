package com.lms.repository;

import com.lms.entity.BookEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetAllBooks() {
        //Assert
        Assert.assertEquals(((List<BookEntity>) bookRepository.findAll()).size(), 4);
    }

    @Test
    public void testGetAllBooksWithQuantityGrtZero() {
        //Arrange
        BookEntity entity = new BookEntity("Scala", 0);
        bookRepository.save(entity);

        //Act
        List<BookEntity> list = bookRepository.findByQuantityGreaterThan(0);
        boolean exist = list.stream().anyMatch((i) -> i.getQuantity() == 0);

        //Assert
        Assert.assertFalse(exist);
    }


}
