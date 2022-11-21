package com.anna.e_bookstore.exception.author;

import com.anna.e_bookstore.controller.AuthorController;
import net.minidev.json.JSONObject;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ControllerAdvice
public class AuthorNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(AuthorNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  EntityModel<JSONObject> authorNotFoundHandler(AuthorNotFoundException exception){
    Map<String, String> message = new HashMap<>();
    message.put("type", "ERROR");
    message.put("message", exception.getMessage());
    return EntityModel.of(new JSONObject(message),
            linkTo(methodOn(AuthorController.class).getAll()).withRel("authors"));
  }
}
