package com.anna.e_bookstore.assembler;

import com.anna.e_bookstore.controller.BookController;
import com.anna.e_bookstore.model.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {
  @Override
  public EntityModel<Book> toModel(Book book) {
    return EntityModel.of(book,
            linkTo(methodOn(BookController.class).get(book.getId())).withSelfRel(),
            linkTo(methodOn(BookController.class).getAll()).withRel("books"));
  }
}
