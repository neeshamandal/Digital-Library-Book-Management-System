package com.library.management.repo;

import com.library.management.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String>{
    List<Book> findByBookIdOrTitleIgnoreCase(String bookId, String title);
    boolean existsById(String id);


}
