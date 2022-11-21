package com.anna.e_bookstore.service.genre;

import com.anna.e_bookstore.model.Genre;

import java.util.List;

public interface GenreService {
  List<Genre> getAll();
  Genre get(Long genreId);
}
