package com.anna.e_bookstore.controller;

import com.anna.e_bookstore.assembler.BookModelAssembler;
import com.anna.e_bookstore.dto.BookDto;
import com.anna.e_bookstore.dto.BookDtoConversion;
import com.anna.e_bookstore.exception.author.AuthorNotFoundException;
import com.anna.e_bookstore.exception.book.BookNotFoundException;
import com.anna.e_bookstore.exception.genre.GenreNotFoundException;
import com.anna.e_bookstore.exception.series.SeriesNotFoundException;
import com.anna.e_bookstore.model.Author;
import com.anna.e_bookstore.model.Book;
import com.anna.e_bookstore.model.Genre;
import com.anna.e_bookstore.model.Series;
import com.anna.e_bookstore.service.author.AuthorService;
import com.anna.e_bookstore.service.book.BookService;
import com.anna.e_bookstore.service.genre.GenreService;
import com.anna.e_bookstore.service.series.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookController {
  private final BookService bookService;
  private final SeriesService seriesService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final BookDtoConversion bookDtoConversion;
  private final BookModelAssembler bookModelAssembler;

  @Autowired
  public BookController(BookService bookService, SeriesService seriesService, AuthorService authorService, GenreService genreService, BookDtoConversion bookDtoConversion, BookModelAssembler bookModelAssembler){
    this.bookService = bookService;
    this.seriesService = seriesService;
    this.authorService = authorService;
    this.genreService = genreService;
    this.bookDtoConversion = bookDtoConversion;
    this.bookModelAssembler = bookModelAssembler;
  }


  /**
   * Retrieve list of books
   * @return CollectionModel<EntityModel<Book>>
   **/
  @GetMapping("/books")
  public CollectionModel<EntityModel<Book>> getAll(){
    List<EntityModel<Book>> books = bookService.getAll().stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(books,
            linkTo(methodOn(BookController.class).getAll()).withSelfRel());
  }

  /**
   * Retrieve details of a book
   * @param id A book's id
   * @exception BookNotFoundException Thrown when a book with the input id is not found
   * @return EntityModel<Book>
   **/
  @GetMapping("/books/{id}")
  public EntityModel<Book> get(@PathVariable Long id){
   Book book = bookService.get(id);

    if (book == null){
      throw new BookNotFoundException(id);
    }

    return bookModelAssembler.toModel(book);
  }

  /**
   * Add standalone book details
   * @return ResponseEntity<?>
   **/
  @PostMapping("/books")
  public ResponseEntity<?> addStandAloneBook(@RequestBody BookDto bookDto){
    Book book = bookDtoConversion.convertToModel(bookDto);
    book.setStandAlone(true);
    bookService.add(book);
    EntityModel<Book> entityModel = bookModelAssembler.toModel(bookService.get(book.getId()));
    return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
  }

  /**
   * Add series book details
   * @param id An id of a book series
   * @exception SeriesNotFoundException Thrown when a book series with the input id is not found
   * @return ResponseEntity<?>
   **/
  @PostMapping("/series/{id}/books")
  public ResponseEntity<?> addSeriesBook(@PathVariable Long id, @RequestBody BookDto bookDto){
    Series series = seriesService.get(id);

    if (series == null){
      throw new SeriesNotFoundException(id);
    }

    Book book = bookDtoConversion.convertToModel(bookDto);
    book.setStandAlone(false);
    book.setSeries(series);
    bookService.add(book);
    EntityModel<Book> entityModel = bookModelAssembler.toModel(bookService.get(book.getId()));
    return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
  }

  /**
   * Retrieve books within a book series
   * @param id An id of a book series
   * @exception SeriesNotFoundException Thrown when a book series with the input id is not found
   * @return CollectionModel<EntityModel<Author>>
   **/
  @GetMapping("/series/{id}/books")
  public CollectionModel<EntityModel<Book>> getSeriesBooks(@PathVariable Long id){
    Series series = seriesService.get(id);

    if (series == null){
      throw new SeriesNotFoundException(id);
    }

    List<EntityModel<Book>> books = bookService.getSeriesBooks(id).stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(books,
            linkTo(methodOn(BookController.class).getSeriesBooks(id)).withSelfRel(),
            linkTo(methodOn(BookController.class).getAll()).withRel("all_books"));
  }

  /**
   * Retrieve a specific author's books
   * @param id An id of an author
   * @exception AuthorNotFoundException Thrown when an author with the input id is not found
   * @return CollectionModel<EntityModel<Book>>
   **/
  @GetMapping("/authors/{id}/books")
  public CollectionModel<EntityModel<Book>> getAuthorBooks(@PathVariable Long id){
    Author author = authorService.get(id);

    if (author == null){
      throw new AuthorNotFoundException(id);
    }

    List<EntityModel<Book>> books = bookService.getAuthorBooks(author.getId()).stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(books,
            linkTo(methodOn(BookController.class).getAuthorBooks(id)).withSelfRel(),
            linkTo(methodOn(BookController.class).getAll()).withRel("all_books"));
  }

  @GetMapping("/genres/{id}/books")
  public CollectionModel<EntityModel<Book>> getGenreBooks(@PathVariable Long id){
    Genre genre = genreService.get(id);

    if (genre == null){
      throw new GenreNotFoundException(id);
    }

    List<EntityModel<Book>> books = bookService.getGenreBooks(genre.getId()).stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(books,
            linkTo(methodOn(BookController.class).getGenreBooks(id)).withSelfRel(),
            linkTo(methodOn(BookController.class).getAll()).withRel("all_books"));
  }

  /**
   * Retrieve related books
   * @param id An id of a book
   * @exception BookNotFoundException Thrown when a book with the input id is not found
   * @return CollectionModel<EntityModel<Book>>
   **/
  @GetMapping("/books/{id}/related-books")
  public CollectionModel<EntityModel<Book>> getRelatedBooks(@PathVariable Long id){
    Book book = bookService.get(id);

    if (book == null){
      throw new BookNotFoundException(id);
    }

    List<EntityModel<Book>> authorBooks = bookService.getAuthorBooks(book.getId()).stream()
            .map(bookModelAssembler::toModel).toList();

    List<EntityModel<Book>> relatedBooks = new ArrayList<>(authorBooks);

    for (Genre genre: book.getGenres()){
      List<Book> genreBooks = bookService.getGenreBooks(genre.getId());
      for (Book genreBook: genreBooks){
        EntityModel<Book> entityModel = bookModelAssembler.toModel(genreBook);
        if (!relatedBooks.contains(entityModel)){
          relatedBooks.add(entityModel);
        }
      }
    }

    relatedBooks.remove(bookModelAssembler.toModel(book));

    return CollectionModel.of(relatedBooks,
            linkTo(methodOn(BookController.class).getRelatedBooks(id)).withSelfRel(),
            linkTo(methodOn(BookController.class).getAll()).withRel("all_books"));
  }

  @GetMapping("/popular-books")
  public CollectionModel<EntityModel<Book>> getPopularBooks(@RequestParam int limit){
    List<EntityModel<Book>> books = bookService.getPopularBooks(limit).stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(books,
            linkTo(methodOn(BookController.class).getPopularBooks(limit)).withSelfRel(),
            linkTo(methodOn(BookController.class).getAll()).withRel("all_books"));
  }

  @PutMapping("/books/{id}/rating")
  public ResponseEntity<?> rate(@PathVariable Long id, @RequestParam int rating){
    Book book = bookService.get(id);

    if (book == null){
      throw new BookNotFoundException(id);
    }

    bookService.rate(book, rating);

    EntityModel<Book> entityModel = bookModelAssembler.toModel(bookService.get(book.getId()));

    return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
  }
}
