package vn.omdinh.demo.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
