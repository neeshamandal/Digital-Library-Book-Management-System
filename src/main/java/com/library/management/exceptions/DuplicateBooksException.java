package com.library.management.exceptions;

import lombok.Getter;

@Getter
public class DuplicateBooksException extends BookException{
    private final String bookId;

    public DuplicateBooksException(String bookId) {
        super("Book already exists with id: " + bookId);
        this.bookId = bookId;
    }
}
