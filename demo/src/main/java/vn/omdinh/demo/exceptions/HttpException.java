package vn.omdinh.demo.exceptions;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
public class HttpException extends RuntimeException {
    HttpStatus status;
    LocalDateTime timestamp;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionResponse toExceptionResponse(String path) {
        return new ExceptionResponse(
            this.status.getReasonPhrase(),
            this.status.value(),
            this.timestamp.toString(),
            this.getMessage(),
            path
        );
    }
}
