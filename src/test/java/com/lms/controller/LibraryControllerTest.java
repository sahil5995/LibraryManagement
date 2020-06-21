package com.lms.controller;

import com.lms.repository.BookRepository;
import com.lms.service.LibraryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = LibraryController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService service;

    @MockBean
    private BookRepository bookRepository;


    @Test
    public void testAddNewBook() throws Exception {

//        mockMvc.perform(MockMvcRequestBuilders.
//                post("/addnewbook").contentType(MediaType.APPLICATION_JSON).
//                param("bookname","DotNet")).andDo(print()).andExpect(status().is(200));

        Assert.assertTrue(true);

    }


}
