package com.anna.e_bookstore.exception.series;

public class SeriesNotFoundException extends RuntimeException {
  public SeriesNotFoundException(Long seriesId){
    super("Could not find series with id " + seriesId);
  }
}
