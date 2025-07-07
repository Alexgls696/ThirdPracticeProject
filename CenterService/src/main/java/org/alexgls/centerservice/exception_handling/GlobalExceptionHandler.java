package org.alexgls.centerservice.exception_handling;

import lombok.RequiredArgsConstructor;
import org.alexgls.centerservice.client.exception.BindException;
import org.alexgls.centerservice.client.exception.NoSuchCreditStoryException;
import org.alexgls.centerservice.client.exception.NoSuchUserException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
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
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

    @ExceptionHandler(NoSuchCreditStoryException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchCreditStoryException(NoSuchCreditStoryException exception, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                messageSource.getMessage("errors.credit_story_not_found", new Object[0], "errors.credit_story_not_found", locale));
        problemDetail.setProperty("error", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException exception, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                messageSource.getMessage("errors.bind", new Object[0], "errors.bind", locale));
        problemDetail.setProperty("errors", exception.getErrors());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problemDetail);
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(org.springframework.validation.BindException exception, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                messageSource.getMessage("errors.bind", new Object[0], "errors.bind", locale));
        problemDetail.setProperty("errors", exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception exception, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,messageSource
                .getMessage("errors.global_exception",new Object[0], "errors.global_exception", locale));
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        problemDetail.setProperty("errors", errors);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problemDetail);
    }

}
