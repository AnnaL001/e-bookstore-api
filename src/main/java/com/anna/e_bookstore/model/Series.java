package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series")
public class Series {
  @Id
  @GeneratedValue
  private Long id;
  private String title;

  @OneToOne(mappedBy = "series")
  private BookSeries bookSeries;

  @OneToMany(mappedBy = "series")
  List<AuthorSeries> series;

  public Series() {
  }

  public Series(String title) {
    this.title = title;
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

  public BookSeries getBookSeries() {
    return bookSeries;
  }

  public void setBookSeries(BookSeries bookSeries) {
    this.bookSeries = bookSeries;
  }

  public List<AuthorSeries> getSeries() {
    return series;
  }

  public void setSeries(List<AuthorSeries> series) {
    this.series = series;
  }
}
