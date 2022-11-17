package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "genre_books")
public class GenreBooks {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "genre_id", referencedColumnName = "id")
  private Genre genre;

  @Column(name = "created_at")
  private Timestamp createdAt;

  private LocalDate dateCreated;

  public GenreBooks() {
  }

  public GenreBooks(Book book, Genre genre) {
    this.book = book;
    this.genre = genre;
    this.createdAt = new Timestamp(new Date().getTime());
    setDateCreated(this.createdAt);
  }

  public Long getId() {
    return id;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Timestamp createdAt) {
    Date date = new Date(createdAt.getTime());
    this.dateCreated = date.toInstant()
            .atZone(ZoneId.of("Africa/Nairobi"))
            .toLocalDate();
  }
}
