package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "author_genres")
public class AuthorGenres {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private Author author;

  @ManyToOne
  @JoinColumn(name = "genre_id", referencedColumnName = "id")
  private Genre genre;

  @Column(name = "created_at")
  private Timestamp createdAt;

  private LocalDate dateCreated;

  public AuthorGenres() {
  }

  public AuthorGenres(Author author, Genre genre) {
    this.author = author;
    this.genre = genre;
    this.createdAt = new Timestamp(new Date().getTime());
    setDateCreated(this.createdAt);
  }

  public Long getId() {
    return id;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
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
