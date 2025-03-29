package com.library.management.repo;

import com.library.management.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,String> {
    Page<Book> findByBookIdOrTitleIgnoreCase(String bookId, String title, Pageable pageable);
    boolean existsById(String id);


}
