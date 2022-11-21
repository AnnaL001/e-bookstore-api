package com.anna.e_bookstore.dao.series;

import com.anna.e_bookstore.model.Series;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PsqlSeriesDaoImpl implements PsqlSeriesDao{
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Series> getAll() {
    String selectQuery = "SELECT s FROM Series s ORDER BY s.title";
    return (List<Series>) entityManager.createQuery(selectQuery).getResultList();
  }

  @Override
  public Series get(Long seriesId) {
    return entityManager.find(Series.class, seriesId);
  }

  @Override
  public void add(Series series) {
    entityManager.persist(series);
  }

}
