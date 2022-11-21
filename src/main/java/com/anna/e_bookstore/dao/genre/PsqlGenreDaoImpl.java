package com.anna.e_bookstore.dao.genre;

import com.anna.e_bookstore.model.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PsqlGenreDaoImpl implements PsqlGenreDao{
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Genre> getAll() {
    String selectQuery = "SELECT g FROM Genre g ORDER BY g.title";
    return (List<Genre>) entityManager.createQuery(selectQuery).getResultList();
  }

  @Override
  public Genre get(Long genreId) {
    return entityManager.find(Genre.class, genreId);
  }
}
