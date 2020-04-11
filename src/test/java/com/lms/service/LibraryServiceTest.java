package com.lms.service;

import com.lms.entity.BookEntity;
import com.lms.entity.UserBookEntity;
import com.lms.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private DataService dataService;

    @Test
    public void getAllBooksTest() {
        //Arrange
        List<BookEntity> list = new ArrayList<>();
        list.add(new BookEntity("Java", 4));
        list.add(new BookEntity("Perl", 5));
        list.add(new BookEntity("Scala", 6));
        Mockito.when(dataService.getAllBooksFromDB()).thenReturn(list);

        //Act
        List<BookEntity> result = libraryService.getAllBooks();

        //Assert
        assertEquals(result.size(), 3);
    }

    @Test
    public void testBorrowBookSuccess() {
        //Arrange
        BookEntity bookEntity = new BookEntity("Java", 3);
        Mockito.when(dataService.findBookById(anyLong())).thenReturn(bookEntity);
        Mockito.when(dataService.isBorrowLimitFull(anyString())).thenReturn(false);
        doNothing().when(dataService).addBookToUser(any());
        doNothing().when(dataService).saveBook(any());

        //Act
        int result = libraryService.borrowBook("1");

        //Assert
        assertEquals(result, 1);
    }

    @Test
    public void testBorrowBookFail() {
        //Arrange
        BookEntity bookEntity = new BookEntity("Java", 3);
        Mockito.when(dataService.findBookById(anyLong())).thenReturn(bookEntity);
        Mockito.when(dataService.isBorrowLimitFull(anyString())).thenReturn(true);

        //Act
        int result = libraryService.borrowBook("1");

        //Assert
        assertEquals(result, 0);
    }

    @Test
    public void getUserBooksTest() {
        //Arrange
        List<UserBookEntity> list = new ArrayList<>();
        list.add(new UserBookEntity("Java"));
        list.add(new UserBookEntity("Perl"));
        Mockito.when(dataService.getUserBooksFromDB(anyString())).thenReturn(list);

        //Act
        List<UserBookEntity> result = libraryService.getUserBooks(Constants.USERNAME);

        //Assert
        assertEquals(result.size(), 2);
    }


}
