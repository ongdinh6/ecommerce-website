package vn.omdinh.demo.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vn.omdinh.demo.annotations.PasswordMatching;

@PasswordMatching(
    password = "password",
    confirmPassword = "rePassword",
    message = "Password & Retype Password must match!"
)
public record RegisterUserRequest(
    @NotNull @NotBlank(message = "Email is required!") @Email(message = "Email invalid!") String email,
    @NotNull @NotBlank(message = "Password is required!") String password,
    @NotNull @NotBlank(message = "Retype Password is required!") String rePassword
) {}
