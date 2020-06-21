package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.repository.BookRepository;
import com.lms.repository.UserBookRepository;
import com.lms.utils.Properties;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DataServiceTest {

    @InjectMocks
    private DataService dataService;

    @Mock
    private UserBookRepository userBookRepo;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<UserBookEntity> userBookArgument;

    @Captor
    private ArgumentCaptor<BookEntity> bookArgument;


    public DataServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIssueBookToUser() {
        //Arrange and Act
        dataService.issueBookToUser("Java");
        verify(userBookRepo).save(userBookArgument.capture());

        //Assert
        Assert.assertEquals(userBookArgument.getValue().getStatus(), Properties.BORROWED);

    }

    @Test
    public void testUpdateUserBookStatus() {
        //Arrange
        UserBookEntity userBookEntity = getUserBookEntity("PHP");
        Mockito.when(userBookRepo.findByUsernameAndBookname(anyString(), anyString())).thenReturn(userBookEntity);

        //Act
        dataService.updateUserBookStatus(Properties.USERNAME, "Java");
        verify(userBookRepo).save(userBookArgument.capture());

        //Assert
        Assert.assertEquals(userBookArgument.getValue().getStatus(), Properties.RETURNED);

    }

    @Test
    public void testUpdateQuantityForBookMinus() {
        //Arrange
        BookEntity bookEntity = getBookEntity();
        Mockito.when(bookRepository.findByName(anyString())).thenReturn(bookEntity);

        //Act
        dataService.updateQuantityForBook(Properties.USERNAME, -1);
        verify(bookRepository).save(bookArgument.capture());

        //Assert
        Assert.assertEquals(bookArgument.getValue().getQuantity(), 2);

    }


    @Test
    public void testUpdateQuantityForBookPlus() {
        //Arrange
        BookEntity bookEntity = getBookEntity();
        Mockito.when(bookRepository.findByName(anyString())).thenReturn(bookEntity);

        //Act
        dataService.updateQuantityForBook(Properties.USERNAME, 1);
        verify(bookRepository).save(bookArgument.capture());

        //Assert
        Assert.assertEquals(bookArgument.getValue().getQuantity(), 4);

    }

    @Test
    public void testIsBookAlreadyBorrowedTrue() {
        //Arrange
        UserBookEntity userBookEntity = getUserBookEntity("Java");
        Mockito.when(userBookRepo.findByUsernameAndBooknameAndStatus(anyString(),
                anyString(), anyString())).thenReturn(userBookEntity);

        //Act
        boolean result = dataService.isBookAlreadyBorrowed(Properties.USERNAME, "Java");

        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testIsBookAlreadyBorrowedFalse() {
        //Arrange
        Mockito.when(userBookRepo.findByUsernameAndBooknameAndStatus(anyString(),
                anyString(), anyString())).thenReturn(null);

        //Act
        boolean result = dataService.isBookAlreadyBorrowed(Properties.USERNAME, "Java");

        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testIsBorrowLimitFullTrue() {
        //Arrange
        List<UserBookEntity> list = new ArrayList<>();
        list.add(getUserBookEntity("Java"));
        list.add(getUserBookEntity("Perl"));
        Mockito.when(userBookRepo.findByUsernameAndStatus(anyString(), anyString())).thenReturn(list);

        //Act
        boolean result = dataService.isBorrowLimitFull(Properties.USERNAME);

        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testIsBorrowLimitFullFalse() {
        //Arrange
        List<UserBookEntity> list = new ArrayList<>();
        list.add(getUserBookEntity("PHP"));
        Mockito.when(userBookRepo.findByUsernameAndStatus(anyString(), anyString())).thenReturn(list);

        //Act
        boolean result = dataService.isBorrowLimitFull(Properties.USERNAME);

        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testFindByID() {
        //Arrange
        BookEntity entity = getBookEntity();
        Mockito.when(bookRepository.findOne(anyLong())).thenReturn(entity);

        //Act
        BookEntity result = dataService.findBookById(1L);

        //Assert
        Assert.assertNotNull(result);
    }

    private UserBookEntity getUserBookEntity(String bookname) {
        UserBookEntity userBookEntity = new UserBookEntity(bookname);
        userBookEntity.setId(1L);
        return userBookEntity;
    }

    private BookEntity getBookEntity() {
        return new BookEntity("Java", 3);
    }

    @Test
    public void addBookToDB() {

        String bookname = "DotNet";

        BookEntity entity = new BookEntity(bookname, 1);
        // bookRepository.save(entity);

        int status = dataService.addNewBook(bookname);
        Assert.assertEquals(8, status);

    }

    @Test
    public void checkNewBookAdded() {

        String bookname = "DotNet";

        BookEntity entity = new BookEntity(bookname, 1);
        // bookRepository.save(entity);

        int status = dataService.addNewBook(bookname);

        verify(bookRepository).save(bookArgument.capture());

        //Assert
        Assert.assertEquals(bookArgument.getValue().getName(), bookname);


    }

    @Test
    public void checkDBError() {
        String bookname = "DotNet";
        Mockito.when(bookRepository.save(any(BookEntity.class))).thenThrow(IllegalArgumentException.class);

        int status = dataService.addNewBook(bookname);

        Assert.assertEquals(0, status);
    }


}
