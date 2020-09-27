package com.spring.library.controller;

import com.spring.library.service.AuthorService;
import com.spring.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return "index";
    }

    @GetMapping("/authorsPage")
    public String viewAuthorsPage(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }

    @GetMapping("/booksPage")
    public String viewBooksPage(Model model, String keyword) {

        if(keyword!=null)
            model.addAttribute("books", bookService.getBooksByKeyword(keyword));
        else
            model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }
}
