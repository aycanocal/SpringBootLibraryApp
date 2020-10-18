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

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    PublisherService publisherService;

    @GetMapping("/addBook")
    public String showBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "add-book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        Author author = authorService.getAuthorByName(book.getAuthor().getAuthorName());
        Publisher publisher = publisherService.getPublisherByName(book.getPublisher().getPublisherName());
        book.setAuthor(author);
        book.setPublisher(publisher);
        bookService.addBook(book);
        return "redirect:/booksPage";
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
