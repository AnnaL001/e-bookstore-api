package com.anna.e_bookstore.dao.author;

import com.anna.e_bookstore.model.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PsqlAuthorDaoImpl implements PsqlAuthorDao{
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Author> getAll() {
    String selectQuery = "SELECT a FROM Author a ORDER BY a.fullname";
    return (List<Author>) entityManager.createQuery(selectQuery).getResultList();
  }

  @Override
  public Author get(Long authorId) {
    return entityManager.find(Author.class, authorId);
  }

  @Override
  public List<Author> getPopularAuthors(int limit) {
    String selectQuery = "SELECT a FROM Author a ORDER BY a.reads DESC";
    return (List<Author>) entityManager.createQuery(selectQuery).setMaxResults(limit).getResultList();
  }

  @Override
  public void add(Author author) {
    entityManager.persist(author);
  }

  @Override
  public void update(Author author) {
    // TO DO
  }
}
