package com.anna.e_bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "series")
public class Series {
  @Id
  @GeneratedValue
  private Long id;
  private String title;

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
}
