package com.spring.library.service;

import com.spring.library.entity.Publisher;
import com.spring.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers;
    }

    public Publisher getPublisher(long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Publisher publisher, Long id) {
        Publisher publisherFromDb = publisherRepository.getOne(id);
        publisherFromDb.setPublisherName(publisher.getPublisherName());
        publisherFromDb.setPublisherDescription(publisher.getPublisherDescription());
        publisherRepository.save(publisherFromDb);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
