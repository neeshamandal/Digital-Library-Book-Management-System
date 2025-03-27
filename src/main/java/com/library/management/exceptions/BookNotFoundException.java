package com.library.management.exceptions;

public class BookNotFoundException extends BookException{
    public BookNotFoundException(){
        super("Book not found ");
    }
}
