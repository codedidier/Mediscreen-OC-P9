package com.codedidier.mshistory.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public final ResponseEntity<String> patientNotFoundException(NoteNotFoundException e) {
        log.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
