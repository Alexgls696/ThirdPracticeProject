package org.example.userdetailsservice.exception_handling;

import lombok.RequiredArgsConstructor;
import org.example.userdetailsservice.exceptions.NoSuchUserException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchUserException(NoSuchUserException exception, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                messageSource.getMessage("errors.user_not_found", new Object[0], "errors.user_not_found", locale));
        problemDetail.setProperty("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }


}
