package com.anna.e_bookstore.dto;

import com.anna.e_bookstore.exception.author.AuthorNotFoundException;
import com.anna.e_bookstore.exception.genre.GenreNotFoundException;
import com.anna.e_bookstore.model.Author;
import com.anna.e_bookstore.model.Book;
import com.anna.e_bookstore.model.FileType;
import com.anna.e_bookstore.model.Genre;
import com.anna.e_bookstore.service.author.AuthorService;
import com.anna.e_bookstore.service.filetype.FileTypeService;
import com.anna.e_bookstore.service.genre.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDtoConversion {
  private final ModelMapper modelMapper;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final FileTypeService fileTypeService;

  @Autowired
  public BookDtoConversion(AuthorService authorService, GenreService genreService, ModelMapper modelMapper, FileTypeService fileTypeService){
    this.authorService = authorService;
    this.genreService = genreService;
    this.modelMapper = modelMapper;
    this.fileTypeService = fileTypeService;
  }

  public Book convertToModel(BookDto bookDto){
    Book book = modelMapper.map(bookDto, Book.class);
    book.setAuthors(getAuthors(bookDto.getAuthorIds()));
    book.setGenres(getGenres(bookDto.getGenreIds()));
    book.setFileType(getFileType(bookDto.getFileTypeId()));
    return book;
  }

  private List<Author> getAuthors(List<Long> authorIds){
    List<Author> authors = new ArrayList<>();

    for (Long authorId: authorIds){
      Author author = authorService.get(authorId);
      if (author == null){
        throw new AuthorNotFoundException(authorId);
      }
      authors.add(author);
    }

    return authors;
  }

  private List<Genre> getGenres(List<Long> genreIds){
    List<Genre> genres = new ArrayList<>();

    for (Long genreId: genreIds){
      Genre genre = genreService.get(genreId);
      if (genre == null){
        throw new GenreNotFoundException(genreId);
      }
      genres.add(genre);
    }

    return genres;
  }

  private FileType getFileType(Long fileTypeId){
    return fileTypeService.get(fileTypeId);
  }
}
