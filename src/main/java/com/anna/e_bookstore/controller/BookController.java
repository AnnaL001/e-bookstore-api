package com.anna.e_bookstore.controller;

import com.anna.e_bookstore.assembler.BookModelAssembler;
import com.anna.e_bookstore.exception.book.BookNotFoundException;
import com.anna.e_bookstore.model.Book;
import com.anna.e_bookstore.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookController {
  private final BookService bookService;
  private final BookModelAssembler bookModelAssembler;

  @Autowired
  public BookController(BookService bookService, BookModelAssembler bookModelAssembler){
    this.bookService = bookService;
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


}
