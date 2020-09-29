package com.spring.library.service;

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import com.spring.library.entity.Publisher;
import com.spring.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
   BookService bookService;

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers;
    }

    public Publisher getPublisher(long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher getPublisherByName(String publisherName) {
        List<Publisher> publishers = publisherRepository.findByPublisherName(publisherName);
        return publishers.get(0);
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

        Publisher publisher = publisherRepository.getOne(id);
        List<Book> books = publisher.getBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            Long bookId = book.getBookId();
            bookService.deleteBook(bookId);
        }
        publisherRepository.deleteById(id);
    }
}
