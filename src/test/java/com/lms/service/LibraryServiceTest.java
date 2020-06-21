package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.utils.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private DataService dataService;

    @Captor
    private ArgumentCaptor<String> dataServiceArgument;

    @Test
    public void getAllBooksTest() {
        //Arrange
        List<BookEntity> list = new ArrayList<>();
        list.add(getBookEntity("Java", 4));
        list.add(getBookEntity("Perl", 5));
        list.add(getBookEntity("Scala", 6));
        Mockito.when(dataService.getAllBooksFromDB()).thenReturn(list);

        //Act
        List<BookEntity> result = libraryService.getAllBooks();

        //Assert
        assertEquals(result.size(), 3);
    }

    @Test
    public void testBorrowBookLimitNo() {
        //Arrange
        BookEntity bookEntity = getBookEntity("Java", 3);
        Mockito.when(dataService.findBookById(anyLong())).thenReturn(bookEntity);
        Mockito.when(dataService.isBorrowLimitFull(anyString())).thenReturn(false);
        Mockito.when(dataService.isBookAlreadyBorrowed(anyString(), anyString())).thenReturn(false);
        doNothing().when(dataService).issueBookToUser(any());

        //Act
        int result = libraryService.borrowBook("1");

        //Assert
        assertEquals(result, 1);
    }

    @Test
    public void testBorrowBookLimitYes() {
        //Arrange
        BookEntity bookEntity = getBookEntity("Java", 3);
        Mockito.when(dataService.findBookById(anyLong())).thenReturn(bookEntity);
        Mockito.when(dataService.isBorrowLimitFull(anyString())).thenReturn(true);

        //Act
        int result = libraryService.borrowBook("1");

        //Assert
        assertEquals(result, 0);
    }


    @Test
    public void testBorrowBookAlreadyBorrowedYes() {
        //Arrange
        BookEntity bookEntity = getBookEntity("Java", 3);
        Mockito.when(dataService.findBookById(anyLong())).thenReturn(bookEntity);
        Mockito.when(dataService.isBorrowLimitFull(anyString())).thenReturn(false);
        Mockito.when(dataService.isBookAlreadyBorrowed(anyString(), anyString())).thenReturn(true);

        //Act
        int result = libraryService.borrowBook("1");

        //Assert
        assertEquals(result, 2);
    }


    @Test
    public void getUserBooksTest() {
        //Arrange
        List<UserBookEntity> list = new ArrayList<>();
        list.add(new UserBookEntity("Java"));
        list.add(new UserBookEntity("Perl"));
        Mockito.when(dataService.getUserBooksFromDB(anyString())).thenReturn(list);

        //Act
        List<UserBookEntity> result = libraryService.getUserBooks(Properties.USERNAME);

        //Assert
        assertEquals(result.size(), 2);
    }

    private BookEntity getBookEntity(String bookname, int quantity) {
        return new BookEntity(bookname, quantity);
    }


    @Test
    public void addBooktest() {

        String bookname = "DotNet";

        Mockito.when(dataService.addNewBook(bookname)).thenReturn(8);


        int status = libraryService.addNewBook(bookname);
        assertEquals(8, status);
        verify(dataService, atLeastOnce()).addNewBook(bookname);

    }


    @Test
    public void checkErrorCase() {
        String bookname = "DotNet";
        Mockito.when(dataService.addNewBook(bookname)).thenReturn(0);

        int status = libraryService.addNewBook(bookname);
        assertEquals(0, status);
        verify(dataService, atLeastOnce()).addNewBook(bookname);

    }


}
