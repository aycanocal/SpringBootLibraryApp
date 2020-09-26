package com.spring.library.controller;


import com.spring.library.entity.Book;
import com.spring.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/authors/{id}/books")
    public void addBook(@PathVariable long id, @RequestBody Book book) {
        bookService.addBook(book, id);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book book) {
        bookService.updateBook(book, id);
    }

    @DeleteMapping("/authors/{authorId}/books/{bookId}")
    public void deleteBook(@PathVariable long authorId, @PathVariable long bookId ) {
        bookService.deleteBook(authorId, bookId);
    }

}
