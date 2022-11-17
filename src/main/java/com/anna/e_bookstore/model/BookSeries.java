package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "book_series")
public class BookSeries {
  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn(name = "series_id", referencedColumnName = "id")
  private Series series;

  @OneToOne
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book book;

  @Column(name = "created_at")
  private Timestamp createdAt;

  private LocalDate dateCreated;

  public BookSeries() {
  }

  public BookSeries(Series series, Book book) {
    this.series = series;
    this.book = book;
    this.createdAt = new Timestamp(new Date().getTime());
    setDateCreated(this.createdAt);
  }

  public Long getId() {
    return id;
  }

  public Series getSeries() {
    return series;
  }

  public void setSeries(Series series) {
    this.series = series;
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
