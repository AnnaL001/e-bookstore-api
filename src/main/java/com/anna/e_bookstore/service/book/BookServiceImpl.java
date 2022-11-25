package com.anna.e_bookstore.service.book;

import com.anna.e_bookstore.dao.book.PsqlBookDao;
import com.anna.e_bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService{
  private final PsqlBookDao bookDao;

  @Autowired
  public BookServiceImpl(PsqlBookDao bookDao){
    this.bookDao = bookDao;
  }

  @Override
  public List<Book> getAll() {
    return bookDao.getAll();
  }

  @Override
  public List<Book> getSeriesBooks(Long seriesId) {
    return bookDao.getSeriesBooks(seriesId);
  }

  @Override
  public List<Book> getAuthorBooks(Long authorId) {
    return bookDao.getAuthorBooks(authorId);
  }

  @Override
  public List<Book> getGenreBooks(Long genreId) {
    return bookDao.getGenreBooks(genreId);
  }

  @Override
  public Book get(Long bookId) {
    return bookDao.get(bookId);
  }

  @Override
  public void add(Book book) {
    bookDao.add(book);
  }

  @Override
  public void rate(Book book, int rating) {
    bookDao.rate(book,rating);
  }
}
