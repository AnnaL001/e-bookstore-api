package com.anna.e_bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "file_types")
public class FileType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  public FileType() {}

  public FileType(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
