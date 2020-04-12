package com.lms.controller;


import com.lms.service.LibraryService;
import com.lms.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class LibraryController {

    @Autowired
    private LibraryService service;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String showBooks(ModelMap model,@RequestParam(defaultValue = "") String status) {
        List listAllBooks = service.getAllBooks();
        List listUserBooks = service.getUserBooks(Properties.USERNAME);

        model.put("listAllBooks", listAllBooks);
        model.put("listUserBooks", listUserBooks);
        model.put("status", status);
        return "BooksView";
    }

    @RequestMapping(value = "/borrow", method = RequestMethod.GET)
    public void borrowBook(@RequestParam String id, HttpServletResponse response) {
       int status = service.borrowBook(id);
        try {
            response.sendRedirect("/books?status="+status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public void returnBook(@RequestParam String bookname, HttpServletResponse response) {
         service.returnBook(bookname);
        try {
            response.sendRedirect("/books?status=3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}