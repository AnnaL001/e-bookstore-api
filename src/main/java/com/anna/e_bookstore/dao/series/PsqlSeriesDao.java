package com.anna.e_bookstore.dao.series;

import com.anna.e_bookstore.model.Series;

import java.util.List;

public interface PsqlSeriesDao {
  List<Series> getAll();
  Series get(Long seriesId);
  void add(Series series);
}
