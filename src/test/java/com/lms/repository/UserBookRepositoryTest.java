package com.lms.repository;

import com.lms.entity.UserBookEntity;
import com.lms.utils.Properties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserBookRepositoryTest {

    @Autowired
    private UserBookRepository userBookRepository;

    @Before
    public void initialiseData() {
        UserBookEntity entity1 = getUserBookEntity("Java", Properties.USERNAME, Properties.BORROWED);
        UserBookEntity entity2 = getUserBookEntity("Perl", Properties.USERNAME, Properties.BORROWED);
        UserBookEntity entity3 = getUserBookEntity("Python", "DummyUser", Properties.BORROWED);

        userBookRepository.save(entity1);
        userBookRepository.save(entity2);
        userBookRepository.save(entity3);
    }

    @Test
    public void testFindByUsernameAndStatus() {
        //Assert
        Assert.assertEquals(userBookRepository.findByUsernameAndStatus(Properties.USERNAME,
                Properties.BORROWED).size(), 2);
    }

    @Test
    public void testGetUserBooks() {
        //Assert
        Assert.assertEquals(userBookRepository.
                findAllByUsernameAndStatus(Properties.USERNAME, Properties.BORROWED).size(), 2);
    }

    @Test
    public void testIfBookAlreadyExistFalse() {
        //Arrange and Act
        UserBookEntity entity = userBookRepository.findByUsernameAndBooknameAndStatus
                (Properties.USERNAME, "Scala", Properties.BORROWED);

        //Assert
        Assert.assertNull(entity);

    }

    @Test
    public void testIfBookAlreadyExistSuccess() {
        //Arrange and Act
        UserBookEntity entity = userBookRepository.findByUsernameAndBooknameAndStatus
                (Properties.USERNAME, "Java", Properties.BORROWED);

        //Assert
        Assert.assertNotNull(entity);
    }

    @Test
    public void testByUsernameAndBookNameExist() {
        //Arrange
        UserBookEntity entity = userBookRepository.findByUsernameAndBookname(Properties.USERNAME, "Java");

        //Assert
        Assert.assertNotNull(entity);
    }

    @Test
    public void testByUsernameAndBookNameNotExist() {
        //Arrange
        UserBookEntity entity = userBookRepository.findByUsernameAndBookname(Properties.USERNAME, "Scala");

        //Assert
        Assert.assertNull(entity);
    }
    
    private UserBookEntity getUserBookEntity(String bookname, String username, String status) {
        UserBookEntity entity1 = new UserBookEntity(bookname);
        entity1.setStatus(status);
        entity1.setUsername(username);
        return entity1;
    }

}
