package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@Tag(name = "Book Management", description = "Endpoints for managing library books")
public class BookController {

    private final BookService bookService;

    @Operation(
            summary = "Add a new book",
            description = "Creates a new book entry in the library")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Book created successfully",
                    content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(examples = @ExampleObject(
                            value = "{\"title\":\"Title is required\",\"author\":\"Author is required\"}")))})
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getValidationErrors(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }


    @Operation(
            summary = "Search books",
            description = "Search books by title, author or other fields with pagination")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Books found",
                    content = @Content(schema = @Schema(implementation = Page.class)))})
    @GetMapping("/search")
    public ResponseEntity<Page<Book>> searchBooks(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String[] sort) {
        Pageable pageable = createPageable(page, size, sort);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.searchBooks(query,pageable));
    }


    @Operation(
            summary = "Get all books",
            description = "Retrieve all books with pagination and sorting")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Books retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class)))})
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String[] sort) {
        Pageable pageable = createPageable(page, size, sort);
        return ResponseEntity.status(HttpStatus.OK).
                body(bookService.getALlBooks(pageable));
    }


    @Operation(
            summary = "Update a book",
            description = "Update an existing book's details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book updated successfully",
                    content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(examples = @ExampleObject(
                            value = "{\"title\":\"Title is required\",\"author\":\"Author is required\"}"
                    ))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found")})
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



    @Operation(
            summary = "Delete a book",
            description = "Remove a book from the library")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Book deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found")})
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