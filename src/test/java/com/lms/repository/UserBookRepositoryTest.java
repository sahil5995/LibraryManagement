package com.lms.repository;

import com.lms.entity.UserBookEntity;
import com.lms.utils.Constants;
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
        UserBookEntity entity1 = getUserBookEntity("Java", Constants.USERNAME, Constants.BORROWED);
        UserBookEntity entity2 = getUserBookEntity("Perl", Constants.USERNAME, Constants.BORROWED);
        UserBookEntity entity3 = getUserBookEntity("Python", "DummyUser", Constants.BORROWED);

        userBookRepository.save(entity1);
        userBookRepository.save(entity2);
        userBookRepository.save(entity3);
    }

    @Test
    public void testFindByUsernameAndStatus() {
        //Assert
        Assert.assertEquals(userBookRepository.findByUsernameAndStatus(Constants.USERNAME,
                Constants.BORROWED).size(), 2);
    }

    @Test
    public void testGetUserBooks() {
        //Assert
        Assert.assertEquals(userBookRepository.
                findAllByUsername(Constants.USERNAME).size(), 2);
    }

    private UserBookEntity getUserBookEntity(String bookname, String username, String status) {
        UserBookEntity entity1 = new UserBookEntity(bookname);
        entity1.setStatus(status);
        entity1.setUsername(username);
        return entity1;
    }


}
