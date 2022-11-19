package com.anna.e_bookstore.service.author;

import com.anna.e_bookstore.dao.author.PsqlAuthorDao;
import com.anna.e_bookstore.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
  private final PsqlAuthorDao authorDao;

  @Autowired
  public AuthorServiceImpl(PsqlAuthorDao authorDao){
    this.authorDao = authorDao;
  }

  @Override
  public List<Author> getAll() {
    return authorDao.getAll();
  }

  @Override
  public Author get(Long authorId) {
    return authorDao.get(authorId);
  }

  @Override
  public void add(Author author) {
    authorDao.add(author);
  }
}
