package com.anna.e_bookstore.controller;

import com.anna.e_bookstore.assembler.SeriesModelAssembler;
import com.anna.e_bookstore.exception.series.SeriesNotFoundException;
import com.anna.e_bookstore.model.Series;
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
public class SeriesController {
  private final SeriesService seriesService;
  private final SeriesModelAssembler seriesModelAssembler;

  @Autowired
  public SeriesController(SeriesService seriesService, SeriesModelAssembler seriesModelAssembler) {
    this.seriesService = seriesService;
    this.seriesModelAssembler = seriesModelAssembler;
  }

  @GetMapping("/series")
  public CollectionModel<EntityModel<Series>> getAll(){
    List<EntityModel<Series>> series = seriesService.getAll().stream()
            .map(seriesModelAssembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(series,
            linkTo(methodOn(SeriesController.class).getAll()).withSelfRel());
  }

  @GetMapping("/series/{id}")
  public EntityModel<Series> get(@PathVariable Long id){
    Series series = seriesService.get(id);

    if (series == null){
      throw new SeriesNotFoundException(id);
    }

    return seriesModelAssembler.toModel(series);
  }

  @PostMapping("/series")
  public ResponseEntity<?> add(@RequestBody Series series){
    seriesService.add(series);
    EntityModel<Series> entityModel = seriesModelAssembler.toModel(seriesService.get(series.getId()));
    return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
  }
}
