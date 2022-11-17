package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "author_books")
public class AuthorBook {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private Author author;

  @ManyToOne
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book book;

  @Column(name = "created_at")
  private Timestamp createdAt;

  private LocalDate dateCreated;

  public AuthorBook() {
  }

  public AuthorBook(Author author, Book book) {
    this.author = author;
    this.book = book;
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

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
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
