package vn.omdinh.demo.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Nonnull;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@RestControllerAdvice
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RequestExceptionHandler.class);

    private String getRequestURI(WebRequest webRequest) {
        return ((ServletWebRequest)webRequest).getRequest().getRequestURI();
    }

    @ExceptionHandler(value = {
        Exception.class,
        HttpException.class,
        IllegalArgumentException.class,
        AuthenticationException.class,
        AccessDeniedException.class,
        ExpiredJwtException.class,
    })
    ResponseEntity<Object> handleHttpException(WebRequest request, Exception exception) {
        logger.error(exception.getLocalizedMessage(), exception);

        if (exception instanceof HttpException e) {
            return ResponseEntity.status(e.status).body(e.toExceptionResponse(getRequestURI(request)));
        }

        if (exception instanceof AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new HttpException(
                    HttpStatus.FORBIDDEN,
                    e.getLocalizedMessage()
            ).toExceptionResponse(getRequestURI(request)));
        }

        if (exception instanceof AuthenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HttpException(
                HttpStatus.UNAUTHORIZED,
                "Username or password is incorrect!"
            ).toExceptionResponse(getRequestURI(request)));
        }

        if (exception instanceof ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HttpException(
                    HttpStatus.UNAUTHORIZED,
                    e.getLocalizedMessage()
            ).toExceptionResponse(getRequestURI(request)));
        }

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new HttpException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getLocalizedMessage()).toExceptionResponse(getRequestURI(request)));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        @Nonnull HttpHeaders headers,
        @Nonnull HttpStatusCode status,
        @Nonnull WebRequest request
    ) {
        var errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        HttpException httpException = new HttpException(HttpStatus.BAD_REQUEST, String.join(",", errors));

        return handleExceptionInternal(ex, httpException.toExceptionResponse(getRequestURI(request)), headers, httpException.status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatusCode status,
            @Nonnull WebRequest request
    ) {
        HttpException httpException = new HttpException(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());

        return handleExceptionInternal(ex,
                httpException.toExceptionResponse(getRequestURI(request)),
                headers,
                httpException.status, request
        );
    }
}
