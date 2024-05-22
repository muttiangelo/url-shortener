package dev.mutti.urlshortener.infra;

import dev.mutti.urlshortener.exceptions.InvalidUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidUrlException(InvalidUrlException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(error.status()).body(error);
    }
}
