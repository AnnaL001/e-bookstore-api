package com.anna.e_bookstore.exception.book;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BookNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  JSONObject bookNotFoundHandler(BookNotFoundException exception){
    Map<String, String> message = new HashMap<>();
    message.put("type", "ERROR");
    message.put("message", exception.getMessage());
    return new JSONObject(message);
  }
}
