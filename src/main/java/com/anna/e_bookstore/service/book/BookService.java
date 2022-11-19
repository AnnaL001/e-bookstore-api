package com.anna.e_bookstore.service.book;

import com.anna.e_bookstore.model.Book;

import java.util.List;

public interface BookService {
  List<Book> getAll();
  Book get(Long bookId);
  void add(Book book);
}
