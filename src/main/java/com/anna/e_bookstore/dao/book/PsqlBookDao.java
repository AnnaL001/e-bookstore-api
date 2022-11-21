package com.anna.e_bookstore.dao.book;

import com.anna.e_bookstore.model.Book;

import java.util.List;

public interface PsqlBookDao {
  List<Book> getAll();
  List<Book> getSeriesBooks(Long seriesId);
  Book get(Long bookId);
  void add(Book book);
  void update(Book book);
}
