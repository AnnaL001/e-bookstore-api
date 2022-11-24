package com.anna.e_bookstore.controller;

import com.anna.e_bookstore.assembler.GenreModelAssembler;
import com.anna.e_bookstore.exception.genre.GenreNotFoundException;
import com.anna.e_bookstore.model.Genre;
import com.anna.e_bookstore.service.genre.GenreService;
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
public class GenreController {
  private final GenreService genreService;
  private final GenreModelAssembler genreModelAssembler;

  @Autowired
  public GenreController(GenreService genreService, GenreModelAssembler genreModelAssembler) {
    this.genreService = genreService;
    this.genreModelAssembler = genreModelAssembler;
  }

  /**
   * Retrieve a list of genres
   * @return CollectionModel<EntityModel<Genre>>
   **/
  @GetMapping("/genres")
  public CollectionModel<EntityModel<Genre>> getAll(){
    List<EntityModel<Genre>> genres = genreService.getAll().stream()
            .map(genreModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(genres,
            linkTo(methodOn(GenreController.class).getAll()).withSelfRel());
  }

  /**
   * Retrieve a specific genre's details
   * @param id An id of a genre
   * @exception GenreNotFoundException Thrown when a genre with the input id is not found
   * @return EntityModel<Genre>
   **/
  @GetMapping("/genres/{id}")
  public EntityModel<Genre> get(@PathVariable Long id){
    Genre genre = genreService.get(id);

    if (genre == null){
      throw new GenreNotFoundException(id);
    }

    return genreModelAssembler.toModel(genre);
  }
}
