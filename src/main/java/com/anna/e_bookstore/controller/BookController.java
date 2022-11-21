package com.anna.e_bookstore.controller;

import com.anna.e_bookstore.assembler.BookModelAssembler;
import com.anna.e_bookstore.dto.BookDto;
import com.anna.e_bookstore.dto.BookDtoConversion;
import com.anna.e_bookstore.exception.book.BookNotFoundException;
import com.anna.e_bookstore.exception.series.SeriesNotFoundException;
import com.anna.e_bookstore.model.Book;
import com.anna.e_bookstore.model.Series;
import com.anna.e_bookstore.service.book.BookService;
import com.anna.e_bookstore.service.series.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookController {
  private final BookService bookService;
  private final SeriesService seriesService;
  private final BookDtoConversion bookDtoConversion;
  private final BookModelAssembler bookModelAssembler;

  @Autowired
  public BookController(BookService bookService, SeriesService seriesService, BookDtoConversion bookDtoConversion, BookModelAssembler bookModelAssembler){
    this.bookService = bookService;
    this.seriesService = seriesService;
    this.bookDtoConversion = bookDtoConversion;
    this.bookModelAssembler = bookModelAssembler;
  }

  @GetMapping("/books")
  public CollectionModel<EntityModel<Book>> getAll(){
    List<EntityModel<Book>> books = bookService.getAll().stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(books,
            linkTo(methodOn(BookController.class).getAll()).withSelfRel());
  }

  @GetMapping("/books/{id}")
  public EntityModel<Book> get(@PathVariable Long id){
   Book book = bookService.get(id);

    if (book == null){
      throw new BookNotFoundException(id);
    }

    return bookModelAssembler.toModel(book);
  }

  @GetMapping("/series/{id}/books")
  public CollectionModel<EntityModel<Book>> getSeriesBooks(@PathVariable Long id){
    List<EntityModel<Book>> books = bookService.getSeriesBooks(id).stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(books,
            linkTo(methodOn(BookController.class).getSeriesBooks(id)).withSelfRel(),
            linkTo(methodOn(BookController.class).getAll()).withRel("all_books"));
  }

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


}
