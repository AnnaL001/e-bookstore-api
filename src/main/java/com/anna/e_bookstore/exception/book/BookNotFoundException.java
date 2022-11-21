package com.anna.e_bookstore.exception.book;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(Long bookId){
    super("Could not find book with id " + bookId);
  }

}
