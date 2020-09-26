package com.spring.library.repository;

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookName(String bookName);

}
