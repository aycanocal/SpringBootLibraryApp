package com.spring.library.controller;

import com.spring.library.entity.Author;
import com.spring.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    @Autowired
    AuthorService authorService;

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

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authorsPage";
    }

}
