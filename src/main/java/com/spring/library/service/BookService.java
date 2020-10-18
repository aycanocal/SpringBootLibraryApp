package com.spring.library.service;

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import com.spring.library.entity.Publisher;
import com.spring.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByKeyword(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void addBook(Book book) {
    Author author = book.getAuthor();
    author.getBooks().add(book);
    Publisher publisher = book.getPublisher();
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
