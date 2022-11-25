package com.anna.e_bookstore.assembler;

import com.anna.e_bookstore.controller.AuthorController;
import com.anna.e_bookstore.controller.BookController;
import com.anna.e_bookstore.model.Author;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthorModelAssembler implements RepresentationModelAssembler<Author, EntityModel<Author>> {
  @Override
  public EntityModel<Author> toModel(Author author) {
    return EntityModel.of(author,
            linkTo(methodOn(AuthorController.class).get(author.getId())).withSelfRel(),
            linkTo(methodOn(BookController.class).getAuthorBooks(author.getId())).withRel("author_books"),
            linkTo(methodOn(AuthorController.class).getAll()).withRel("authors"));
  }
}
