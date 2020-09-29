package com.spring.library.service;

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import com.spring.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookService bookService;

    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author getAuthorByName(String authorName) {
        List<Author> authors = authorRepository.findByAuthorName(authorName);
        return authors.get(0);
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public void updateAuthor(Author author, Long id) {
        Author authorFromDb = authorRepository.getOne(id);
        authorFromDb.setAuthorName(author.getAuthorName());
        authorFromDb.setAuthorDescription(author.getAuthorDescription());
        authorRepository.save(authorFromDb);
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.getOne(id);
        List<Book> books = author.getBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            Long bookId = book.getBookId();
            bookService.deleteBook(bookId);
        }
        authorRepository.deleteById(id);
    }
}
