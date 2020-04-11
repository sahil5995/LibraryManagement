package com.lms.service;

import com.lms.entity.BookEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private DataService dataService;

    @Test
    public void getAllBooksTest() {

        List<BookEntity> list = new ArrayList<>();
        list.add(new BookEntity("Java", 4));
        list.add(new BookEntity("Perl", 5));
        list.add(new BookEntity("Scala", 6));

        Mockito.when(dataService.getAllBooksFromDB()).thenReturn(list);
        List<BookEntity> result = libraryService.getAllBooks();

        assertEquals(result.size(), 3);
    }
}
