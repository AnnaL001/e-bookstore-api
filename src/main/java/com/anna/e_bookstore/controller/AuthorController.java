package com.anna.e_bookstore.controller;

import com.anna.e_bookstore.assembler.AuthorModelAssembler;
import com.anna.e_bookstore.exception.author.AuthorNotFoundException;
import com.anna.e_bookstore.model.Author;
import com.anna.e_bookstore.service.author.AuthorService;
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
public class AuthorController {
  private final AuthorService authorService;
  private final AuthorModelAssembler authorModelAssembler;

  @Autowired
  public AuthorController(AuthorService authorService, AuthorModelAssembler authorModelAssembler){
    this.authorService = authorService;
    this.authorModelAssembler = authorModelAssembler;
  }

  @GetMapping("/authors")
  public CollectionModel<EntityModel<Author>> getAll(){
    List<EntityModel<Author>> authors = authorService.getAll().stream()
            .map(authorModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(authors,
            linkTo(methodOn(AuthorController.class).getAll()).withSelfRel());
  }

  @GetMapping("/authors/{id}")
  public EntityModel<Author> get(@PathVariable Long id){
    Author author = authorService.get(id);

    if (author == null){
      throw new AuthorNotFoundException(id);
    }

    return authorModelAssembler.toModel(author);
  }
}
