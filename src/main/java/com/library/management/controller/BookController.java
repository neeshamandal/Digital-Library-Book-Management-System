package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getValidationErrors(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Book>> searchBooks(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String[] sort) {
        Pageable pageable = createPageable(page, size, sort);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.searchBooks(query,pageable));
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String[] sort) {
        Pageable pageable = createPageable(page, size, sort);
        return ResponseEntity.status(HttpStatus.OK).
                body(bookService.getALlBooks(pageable));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable String bookId,
            @RequestBody @Valid Book bookDetails,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getValidationErrors(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookId, bookDetails));
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    private Map<String, String> getValidationErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    private Pageable createPageable(int page, int size, String[] sort) {
        String sortField = sort[0];
        String direction = sort.length > 1 ? sort[1] : "asc";
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page, size, Sort.by(sortDirection, sortField));
    }

}