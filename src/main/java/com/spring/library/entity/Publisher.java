package com.spring.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id")
    private long publisherId;

    @Column(name="publisher_name")
    private String publisherName;

    @Column(name="publisher_description")
    private String publisherDescription;

    @OneToMany(targetEntity=Book.class)
    @JsonBackReference(value="book-publisher")
    private List<Book> books = new ArrayList<>();

    public Publisher() {

    }

    public Publisher(String publisherName, String publisherDescription) {
        this.publisherName = publisherName;
        this.publisherDescription = publisherDescription;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherDescription() {
        return publisherDescription;
    }

    public void setPublisherDescription(String publisherDescription) {
        this.publisherDescription = publisherDescription;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
