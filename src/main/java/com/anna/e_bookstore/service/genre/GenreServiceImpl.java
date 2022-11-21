package com.anna.e_bookstore.service.genre;

import com.anna.e_bookstore.dao.genre.PsqlGenreDao;
import com.anna.e_bookstore.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {
  private final PsqlGenreDao genreDao;

  @Autowired
  public GenreServiceImpl(PsqlGenreDao genreDao){
    this.genreDao = genreDao;
  }

  @Override
  public List<Genre> getAll() {
    return genreDao.getAll();
  }

  @Override
  public Genre get(Long genreId) {
    return genreDao.get(genreId);
  }
}
