package com.spring.library.service;

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import com.spring.library.repository.AuthorRepository;
import com.spring.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByKeyword(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void addBook(Book book, Long id) {
    Author author = authorRepository.getOne(id);
    book.setAuthor(author);
    author.getBooks().add(book);
    bookRepository.save(book);
    }

    public void updateBook(Book book, Long id) {
        Book bookFromDb = bookRepository.getOne(id);
        bookFromDb.setBookName(book.getBookName());
        bookFromDb.setBookDescription(book.getBookDescription());
        bookFromDb.setIsbn(book.getIsbn());
        bookRepository.save(bookFromDb);
    }

    public void deleteBook(Long authorId, Long bookId) {
        Author author = authorRepository.getOne(authorId);
        Book book = bookRepository.getOne(bookId);
        book.setAuthor(null);
        author.getBooks().remove(book);
        bookRepository.delete(book);
    }



}
