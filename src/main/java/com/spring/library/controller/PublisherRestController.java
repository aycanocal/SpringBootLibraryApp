package com.spring.library.controller;

import com.spring.library.entity.Publisher;
import com.spring.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherRestController {

    @Autowired
    PublisherService publisherService;

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publishers/{id}")
    public Publisher getPublisher(@PathVariable Long id) {
        return publisherService.getPublisher(id);
    }

    @PostMapping("/publishers")
    public void addPublisher(@RequestBody Publisher publisher) {
        publisherService.addPublisher(publisher);
    }

    @PutMapping("/publishers/{id}")
    public void updatePublisher(@RequestBody Publisher publisher, @PathVariable Long id) {
        publisherService.updatePublisher(publisher, id);
    }

    @DeleteMapping("/publishers/{id}")
    public void deletePublisher(@PathVariable long id)
    {
        publisherService.deletePublisher(id);
    }
}
