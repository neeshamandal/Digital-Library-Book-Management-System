package com.library.management.repo;

import com.library.management.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String>{
    Page<Book> findByBookIdOrTitleIgnoreCase(String bookId, String title, Pageable pageable);
    boolean existsById(String id);


}
