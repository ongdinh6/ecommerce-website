package vn.omdinh.demo.models.responses;

public record LoginUserResponse(
    String token,
    long expiresIn
) {
}
