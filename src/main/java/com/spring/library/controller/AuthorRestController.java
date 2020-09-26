package com.spring.library.controller;

import com.spring.library.entity.Author;
import com.spring.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping("/authors")
    public void addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
    }

    @PutMapping("/authors/{id}")
    public void updateAuthor(@RequestBody Author author, @PathVariable Long id) {
        authorService.updateAuthor(author, id);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable long id)
    {
        authorService.deleteAuthor(id);
    }

}
