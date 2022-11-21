package com.anna.e_bookstore.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
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

  @ManyToMany
  @JoinTable(
          name = "author_books",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private List<Author> authors;

  @ManyToMany
  @JoinTable(
          name = "genre_books",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private List<Genre> genres;

  @OneToOne
  @JoinTable(
          name = "book_series",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "series_id")
  )
  private Series series;

  public Book() {
  }

  public Book(String title, String short_bio, int volume, int year, int reads, String publisher, String language, String isbn10, String isbn13, FileType fileType) {
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

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public Series getSeries() {
    if (!isStandAlone){
      return series;
    }
    return null;
  }

  public void setSeries(Series series) {
    if (!isStandAlone){
      this.series = series;
    }
  }
}
