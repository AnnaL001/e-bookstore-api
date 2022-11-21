package com.anna.e_bookstore.dao.filetype;

import com.anna.e_bookstore.model.FileType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class PsqlFileTypeDaoImpl implements PsqlFileTypeDao{
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public FileType get(Long fileTypeId) {
    return entityManager.find(FileType.class, fileTypeId);
  }
}
