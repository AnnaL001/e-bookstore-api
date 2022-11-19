package com.anna.e_bookstore.dao.author;

import com.anna.e_bookstore.model.Author;

import java.util.List;

public interface PsqlAuthorDao {
  List<Author> getAll();
  Author get(Long authorId);
  void add(Author author);
  void update(Author author);
}
