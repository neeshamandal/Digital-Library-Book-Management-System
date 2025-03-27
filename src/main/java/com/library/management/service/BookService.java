package com.library.management.service;

import com.library.management.exceptions.BookNotFoundException;
import com.library.management.exceptions.DuplicateBooksException;
import com.library.management.model.Book;
import com.library.management.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book addBook(Book book) {
        System.out.println("before :" +book);
        if (book.getBookId()!=null && bookRepository.existsById(book.getBookId())) {
            throw new DuplicateBooksException(book.getBookId());
        }
        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String query){
        return bookRepository.findByBookIdOrTitleIgnoreCase(query,query);
    }

    public List<Book> getALlBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book updateBook(String bookId, Book bookDetails){
        Book book = getBookById(bookId);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setAvailability(bookDetails.getAvailability());
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(String bookId){
        bookRepository.delete(getBookById(bookId));
    }

    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);
    }
}
