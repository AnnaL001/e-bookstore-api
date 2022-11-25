package com.anna.e_bookstore.service.author;

import com.anna.e_bookstore.model.Author;

import java.util.List;

public interface AuthorService {
  List<Author> getAll();
  Author get(Long authorId);
  List<Author> getPopularAuthors(int limit);
  void add(Author author);
}
