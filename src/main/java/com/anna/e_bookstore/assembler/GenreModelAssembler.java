package com.anna.e_bookstore.assembler;

import com.anna.e_bookstore.controller.BookController;
import com.anna.e_bookstore.controller.GenreController;
import com.anna.e_bookstore.model.Genre;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GenreModelAssembler implements RepresentationModelAssembler<Genre, EntityModel<Genre>> {
  @Override
  public EntityModel<Genre> toModel(Genre genre) {
    return EntityModel.of(genre,
            linkTo(methodOn(GenreController.class).get(genre.getId())).withSelfRel(),
            linkTo(methodOn(BookController.class).getGenreBooks(genre.getId())).withRel("genre_books"),
            linkTo(methodOn(GenreController.class).getAll()).withRel("genres"));
  }
}
