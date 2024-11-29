package vn.omdinh.demo.exceptions;


public record ExceptionResponse (
    String error,
    int status,
    String timestamp,
    String message,
    String path
) {}
