package com.anna.e_bookstore.exception.genre;

public class GenreNotFoundException extends RuntimeException{
  public GenreNotFoundException(Long genreId){
    super("Could not find genre with id " + genreId);
  }
}
