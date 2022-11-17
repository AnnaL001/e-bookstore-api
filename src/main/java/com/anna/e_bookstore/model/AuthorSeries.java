package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "author_series")
public class AuthorSeries {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private Author author;

  @ManyToOne
  @JoinColumn(name = "series_id", referencedColumnName = "id")
  private Series series;

  @Column(name = "created_at")
  private Timestamp createdAt;

  private LocalDate dateCreated;

  public AuthorSeries() {
  }

  public AuthorSeries(Author author, Series series) {
    this.author = author;
    this.series = series;
    this.createdAt = new Timestamp(new Date().getTime());
    setDateCreated(this.createdAt);
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Series getSeries() {
    return series;
  }

  public void setSeries(Series series) {
    this.series = series;
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
