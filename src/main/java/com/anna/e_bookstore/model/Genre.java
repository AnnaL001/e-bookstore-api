package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
  @Id
  @GeneratedValue
  private Long id;
  private String title;
  private String description;

  @OneToMany(mappedBy = "genre")
  List<AuthorGenres> authors;

  @OneToMany(mappedBy = "genre")
  List<GenreBooks> books;

  public Genre() {
  }

  public Genre(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<AuthorGenres> getAuthors() {
    return authors;
  }

  public void setAuthors(List<AuthorGenres> authors) {
    this.authors = authors;
  }

  public List<GenreBooks> getBooks() {
    return books;
  }

  public void setBooks(List<GenreBooks> books) {
    this.books = books;
  }
}
