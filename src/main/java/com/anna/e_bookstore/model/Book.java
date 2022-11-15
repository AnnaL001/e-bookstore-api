package com.anna.e_bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue
  private int id;
  private String title;
  private String short_bio;
  private int volume;
  private int year;
  private int reads;
  private String publisher;
  private String language;
  private String isbn10;
  private String isbn13;
  @ManyToOne
  @JoinColumn(name = "file_type_id")
  private FileType fileType;
  @Column(name = "is_stand_alone")
  private boolean isStandAlone;

  public Book() {
  }

  public Book(String title, String short_bio, int volume, int year, int reads, String publisher, String language, String isbn10, String isbn13, FileType fileType, boolean isStandAlone) {
    this.title = title;
    this.short_bio = short_bio;
    this.volume = volume;
    this.year = year;
    this.reads = reads;
    this.publisher = publisher;
    this.language = language;
    this.isbn10 = isbn10;
    this.isbn13 = isbn13;
    this.fileType = fileType;
    this.isStandAlone = isStandAlone;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getShort_bio() {
    return short_bio;
  }

  public void setShort_bio(String short_bio) {
    this.short_bio = short_bio;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getReads() {
    return reads;
  }

  public void setReads(int reads) {
    this.reads = reads;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getIsbn10() {
    return isbn10;
  }

  public void setIsbn10(String isbn10) {
    this.isbn10 = isbn10;
  }

  public String getIsbn13() {
    return isbn13;
  }

  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }

  public FileType getFileType() {
    return fileType;
  }

  public void setFileType(FileType fileType) {
    this.fileType = fileType;
  }

  public boolean isStandAlone() {
    return isStandAlone;
  }

  public void setStandAlone(boolean standAlone) {
    isStandAlone = standAlone;
  }
}
