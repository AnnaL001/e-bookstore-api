package com.anna.e_bookstore.service.series;

import com.anna.e_bookstore.dao.series.PsqlSeriesDao;
import com.anna.e_bookstore.model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeriesServiceImpl implements SeriesService{
  private final PsqlSeriesDao seriesDao;

  @Autowired
  public SeriesServiceImpl(PsqlSeriesDao seriesDao){
    this.seriesDao = seriesDao;
  }

  @Override
  public List<Series> getAll() {
    return seriesDao.getAll();
  }

  @Override
  public Series get(Long seriesId) {
    return seriesDao.get(seriesId);
  }

  @Override
  public void add(Series series) {
    seriesDao.add(series);
  }
}
