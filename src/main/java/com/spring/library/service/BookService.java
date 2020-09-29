package com.spring.library.service;

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import com.spring.library.entity.Publisher;
import com.spring.library.repository.AuthorRepository;
import com.spring.library.repository.BookRepository;
import com.spring.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByKeyword(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void addBook(Book book, Long authorId, Long publisherId) {
    Author author = authorRepository.getOne(authorId);
    Publisher publisher = publisherRepository.getOne(publisherId);
    book.setAuthor(author);
    book.setPublisher(publisher);
    author.getBooks().add(book);
    publisher.getBooks().add(book);
    bookRepository.save(book);
    }

    public void updateBook(Book book, Long id) {
        Book bookFromDb = bookRepository.getOne(id);
        bookFromDb.setBookName(book.getBookName());
        bookFromDb.setBookDescription(book.getBookDescription());
        bookFromDb.setIsbn(book.getIsbn());
        bookFromDb.setBookSubName(book.getBookSubName());
        bookFromDb.setBookSerialName(book.getBookSerialName());
        bookFromDb.setPublisher(book.getPublisher());
        bookFromDb.setAuthor(book.getAuthor());
        bookRepository.save(bookFromDb);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.getOne(id);
        Author author = book.getAuthor();
        author.getBooks().remove(book);
        Publisher publisher = book.getPublisher();
        publisher.getBooks().remove(book);
        bookRepository.delete(book);
    }

}
