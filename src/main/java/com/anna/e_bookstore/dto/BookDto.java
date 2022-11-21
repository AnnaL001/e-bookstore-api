package com.anna.e_bookstore.dto;

import java.util.List;

public class BookDto {
  private final String title;
  private final String short_bio;
  private final int volume;
  private final int year;
  private final int reads;
  private final String publisher;
  private final String language;
  private final String isbn10;
  private final String isbn13;
  private final Long fileTypeId;
  private final List<Long> authorIds;
  private final List<Long> genreIds;

  public BookDto(String title, String short_bio, int volume, int year, int reads, String publisher, String language, String isbn10, String isbn13, Long fileTypeId, List<Long> authorIds, List<Long> genreIds) {
    this.title = title;
    this.short_bio = short_bio;
    this.volume = volume;
    this.year = year;
    this.reads = reads;
    this.publisher = publisher;
    this.language = language;
    this.isbn10 = isbn10;
    this.isbn13 = isbn13;
    this.fileTypeId = fileTypeId;
    this.authorIds = authorIds;
    this.genreIds = genreIds;
  }

  public String getTitle() {
    return title;
  }

  public String getShort_bio() {
    return short_bio;
  }

  public int getVolume() {
    return volume;
  }

  public int getYear() {
    return year;
  }

  public int getReads() {
    return reads;
  }

  public String getPublisher() {
    return publisher;
  }

  public String getLanguage() {
    return language;
  }

  public String getIsbn10() {
    return isbn10;
  }

  public String getIsbn13() {
    return isbn13;
  }

  public Long getFileTypeId() {
    return fileTypeId;
  }

  public List<Long> getAuthorIds() {
    return authorIds;
  }

  public List<Long> getGenreIds() {
    return genreIds;
  }
}
