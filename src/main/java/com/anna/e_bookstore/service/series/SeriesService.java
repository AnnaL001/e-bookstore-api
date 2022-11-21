package com.anna.e_bookstore.service.series;

import com.anna.e_bookstore.model.Series;

import java.util.List;

public interface SeriesService {
  List<Series> getAll();
  Series get(Long seriesId);
  void add(Series series);
}
