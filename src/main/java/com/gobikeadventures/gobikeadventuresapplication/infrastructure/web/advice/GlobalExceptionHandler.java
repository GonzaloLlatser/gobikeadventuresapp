package com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.advice;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {

    ErrorResponseDTO error = new ErrorResponseDTO("Failed to process request: " + ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}