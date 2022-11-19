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

}
