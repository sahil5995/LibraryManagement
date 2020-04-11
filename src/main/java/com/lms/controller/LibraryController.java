package com.lms.controller;


import com.lms.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class LibraryController {

    @Autowired
    private LibraryService service;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String showBooks(ModelMap model) {
        List list = service.getAllBooks();
        model.put("list", list);
        return "BooksView";
    }

}