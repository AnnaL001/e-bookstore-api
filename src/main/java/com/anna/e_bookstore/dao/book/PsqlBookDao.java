package com.anna.e_bookstore.dao.book;

import com.anna.e_bookstore.model.Book;

import java.util.List;

public interface PsqlBookDao {
  List<Book> getAll();
  List<Book> getSeriesBooks(Long seriesId);
  List<Book> getAuthorBooks(Long authorId);
  List<Book> getGenreBooks(Long genreId);
  Book get(Long bookId);
  void add(Book book);
  void rate(Book book, int rating);
  void update(Book book);
}
