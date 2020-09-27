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
    @Column(name="book_description")
    private String bookDescription;

    @ManyToOne(targetEntity = Author.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonBackReference
    private Author author;

    public Book() {

    }

    public Book(String isbn, String bookName, String bookDescription,  Author author) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.author = author;
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


}
