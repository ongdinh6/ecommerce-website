package vn.omdinh.demo.services;

import vn.omdinh.demo.dtos.UserDTO;
import vn.omdinh.demo.models.requests.LoginUserRequest;
import vn.omdinh.demo.models.requests.RegisterUserRequest;

public interface AuthenticationService {
    UserDTO signIn(LoginUserRequest loginUserRequest);
    UserDTO signup(RegisterUserRequest registerUserRequest);
}
