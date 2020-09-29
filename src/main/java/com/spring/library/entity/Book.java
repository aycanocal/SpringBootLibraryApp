package com.spring.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private long bookId;

    private String isbn;

    @Column(name="book_name")
    private String bookName;

    @Column(name="book_sub_name")
    private String bookSubName;

    @Column(name="book_serial_name")
    private String bookSerialName;

    @Column(name="book_description")
    private String bookDescription;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonManagedReference(value="book-author")
    private Author author;

    @ManyToOne(targetEntity = Publisher.class)
    @JoinColumn(name = "publisher_id", nullable = false)
    @JsonManagedReference(value="book-publisher")
    private Publisher publisher;

    public Book() {

    }

    public Book(String isbn, String bookName, String bookSubName, String bookSerialName, String bookDescription, Author author, Publisher publisher) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookSubName = bookSubName;
        this.bookSerialName = bookSerialName;
        this.bookDescription = bookDescription;
        this.author = author;
        this.publisher = publisher;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookSubName() {
        return bookSubName;
    }

    public void setBookSubName(String bookSubName) {
        this.bookSubName = bookSubName;
    }

    public String getBookSerialName() {
        return bookSerialName;
    }

    public void setBookSerialName(String bookSerialName) {
        this.bookSerialName = bookSerialName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
