package com.anna.e_bookstore.assembler;

import com.anna.e_bookstore.controller.SeriesController;
import com.anna.e_bookstore.model.Series;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SeriesModelAssembler implements RepresentationModelAssembler<Series, EntityModel<Series>> {
  @Override
  public EntityModel<Series> toModel(Series series) {
    return EntityModel.of(series,
            linkTo(methodOn(SeriesController.class).get(series.getId())).withSelfRel(),
            linkTo(methodOn(SeriesController.class).getAll()).withRel("series"));
  }
}
