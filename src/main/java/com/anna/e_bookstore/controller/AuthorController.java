package com.anna.e_bookstore.controller;

import com.anna.e_bookstore.assembler.AuthorModelAssembler;
import com.anna.e_bookstore.exception.author.AuthorNotFoundException;
import com.anna.e_bookstore.model.Author;
import com.anna.e_bookstore.service.author.AuthorService;
import com.anna.e_bookstore.service.socials.SocialService;
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
public class AuthorController {
  private final AuthorService authorService;
  private final SocialService socialService;
  private final AuthorModelAssembler authorModelAssembler;

  @Autowired
  public AuthorController(AuthorService authorService, SocialService socialService, AuthorModelAssembler authorModelAssembler){
    this.authorService = authorService;
    this.socialService = socialService;
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

  @PostMapping("/authors")
  public ResponseEntity<?> add(@RequestBody Author author){
    socialService.add(author.getSocials());
    authorService.add(author);
    EntityModel<Author> entityModel = authorModelAssembler.toModel(authorService.get(author.getId()));
    return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
  }
}
