package com.spring.library.controller;

import com.spring.library.entity.Author;
import com.spring.library.entity.Publisher;
import com.spring.library.service.AuthorService;
import com.spring.library.service.BookService;
import com.spring.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibraryController {

    @Autowired
    AuthorService authorService;

    @Autowired
    PublisherService publisherService;

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

    @GetMapping("/addAuthor")
    public String showAuthorForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "add-author-form";
    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.addAuthor(author);
        return "redirect:/authorsPage";
    }

    @GetMapping("/addPublisher")
    public String showPublisherForm(Model model) {
        Publisher publisher = new Publisher();
        model.addAttribute("publisher", publisher);
        return "add-publisher-form";
    }

    @PostMapping("/savePublisher")
    public String savePublisher(@ModelAttribute("publisher") Publisher publisher) {
        publisherService.addPublisher(publisher);
        return "redirect:/publishersPage";
    }




}
