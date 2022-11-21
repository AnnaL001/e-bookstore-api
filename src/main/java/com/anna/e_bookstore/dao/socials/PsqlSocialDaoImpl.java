package com.anna.e_bookstore.dao.socials;

import com.anna.e_bookstore.model.Socials;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class PsqlSocialDaoImpl implements PsqlSocialDao {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void add(Socials socials) {
    entityManager.persist(socials);
  }
}
