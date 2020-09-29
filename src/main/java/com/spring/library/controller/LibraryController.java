package com.spring.library.controller;

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import com.spring.library.entity.Publisher;
import com.spring.library.service.AuthorService;
import com.spring.library.service.BookService;
import com.spring.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/addBook")
    public String showBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "add-book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
              bookService.addBook(book, book.getAuthor().getAuthorId(), book.getPublisher().getPublisherId());
        return "redirect:/booksPage";
    }

    @GetMapping("/updateAuthor/{id}")
    public String showUpdateAuthorForm(Model model, @PathVariable Long id) {
        Author author = authorService.getAuthor(id);
        model.addAttribute("author", author);
        return "update-author-form";
    }

    @PostMapping("/saveUpdatedAuthor/{id}")
    public String saveUpdatedAuthor(@ModelAttribute("author") Author author, @PathVariable Long id) {
        authorService.updateAuthor(author, id);
        return "redirect:/authorsPage";
    }

    @GetMapping("/updatePublisher/{id}")
    public String showUpdatePublisherForm(Model model, @PathVariable Long id) {
        Publisher publisher = publisherService.getPublisher(id);
        model.addAttribute("publisher", publisher);
        return "update-publisher-form";
    }

    @PostMapping("/saveUpdatedPublisher/{id}")
    public String saveUpdatedPublisher(@ModelAttribute("publisher") Publisher publisher, @PathVariable Long id) {
        publisherService.updatePublisher(publisher, id);
        return "redirect:/publishersPage";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authorsPage";
    }

    @GetMapping("/deletePublisher/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return "redirect:/publishersPage";
    }

    @GetMapping("/updateBook/{id}")
    public String showUpdateBookForm(Model model, @PathVariable Long id) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "update-book-form";
    }

    @PostMapping("/saveUpdatedBook/{id}")
    public String saveUpdatedBook(@ModelAttribute("book") Book book, @PathVariable Long id) {
        bookService.updateBook(book, id);
        return "redirect:/booksPage";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/booksPage";
    }





}
