package com.anna.e_bookstore.dao.book;

import com.anna.e_bookstore.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PsqlBookDaoImpl implements PsqlBookDao{
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Book> getAll() {
    String selectQuery = "SELECT b FROM Book b ORDER BY b.title";
    return (List<Book>) entityManager.createQuery(selectQuery).getResultList();
  }

  @Override
  public Book get(Long bookId) {
    return entityManager.find(Book.class, bookId);
  }

  @Override
  public void add(Book book) {
    entityManager.persist(book);
  }

  @Override
  public void update(Book book) {
    // TO DO
  }
}
