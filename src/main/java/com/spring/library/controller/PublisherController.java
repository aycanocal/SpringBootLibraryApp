package com.spring.library.controller;

import com.spring.library.entity.Publisher;
import com.spring.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublisherController {

    @Autowired
    PublisherService publisherService;

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

    @GetMapping("/deletePublisher/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return "redirect:/publishersPage";
    }

}
