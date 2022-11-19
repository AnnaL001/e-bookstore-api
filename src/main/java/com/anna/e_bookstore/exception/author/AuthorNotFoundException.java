package com.anna.e_bookstore.exception.author;

public class AuthorNotFoundException extends RuntimeException{
  public AuthorNotFoundException(Long authorId){
    super("Could not find author with id " + authorId);
  }
}
