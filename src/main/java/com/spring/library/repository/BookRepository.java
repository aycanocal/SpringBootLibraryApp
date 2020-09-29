package com.spring.library.repository;

import com.spring.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value="select * from book b inner join author a on (b.author_id = a.author_id) inner join publisher p on (b.publisher_id = p.publisher_id)  where b.book_name like %:keyword% or b.book_description like %:keyword% or b.isbn like %:keyword% or b.book_sub_name like %:keyword% or b.book_serial_name like %:keyword% or a.author_name like %:keyword% or p.publisher_name like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
