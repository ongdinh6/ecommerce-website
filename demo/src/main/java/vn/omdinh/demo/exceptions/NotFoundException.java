package vn.omdinh.demo.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
