package org.omnirio.app.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity userNotFound (Exception ex, WebRequest request) {
        log.error("handling UserNotFound...",ex);
        return ResponseEntity.notFound().build();
    }
}
