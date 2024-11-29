package vn.omdinh.demo.models.requests;

public record LoginUserRequest(
    String email,
    String password
) { }
