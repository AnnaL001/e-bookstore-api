package com.anna.e_bookstore.dao.genre;

import com.anna.e_bookstore.model.Genre;

import java.util.List;

public interface PsqlGenreDao {
  List<Genre> getAll();
  Genre get(Long genreId);
}
