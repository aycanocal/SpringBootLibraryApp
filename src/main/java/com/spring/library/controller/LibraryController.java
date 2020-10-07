package com.spring.library.controller;

import com.spring.library.service.AuthorService;
import com.spring.library.service.BookService;
import com.spring.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @Autowired
    AuthorService authorService;

    @Autowired
    PublisherService publisherService;

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String viewHomePage() {
        return "home";
    }

    @GetMapping("/authorsPage")
    public String viewAuthorsPage(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }

    @GetMapping("/publishersPage")
    public String viewPublishersPage(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "publishers";
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
