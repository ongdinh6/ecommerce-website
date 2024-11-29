package vn.omdinh.demo.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerError extends HttpException {
    public InternalServerError(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
