package de.allianz.project.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RequiredArgsConstructor
@RestControllerAdvice
@Log
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { // ResponseEntityExcepHandler übergibt 2 attribute: Runtime und WebRequest

    private final MessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(RuntimeException exception, WebRequest request) {
        logger.warn("ENTITY NOT FOUND");
        String message = messageSource.getMessage("exception.idNotFound", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
