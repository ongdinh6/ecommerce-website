package vn.omdinh.demo.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    long expiredTime(String token);
}
