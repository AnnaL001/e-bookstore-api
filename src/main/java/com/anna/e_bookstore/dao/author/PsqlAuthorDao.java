package com.anna.e_bookstore.dao.author;

import com.anna.e_bookstore.model.Author;

import java.util.List;

public interface PsqlAuthorDao {
  List<Author> getAll();
  Author get(Long authorId);
  List<Author> getPopularAuthors(int limit);
  void add(Author author);
  void update(Author author);
}
