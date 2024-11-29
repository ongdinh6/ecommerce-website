package vn.omdinh.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.omdinh.demo.dtos.UserDTO;
import vn.omdinh.demo.models.requests.LoginUserRequest;
import vn.omdinh.demo.models.requests.RegisterUserRequest;
import vn.omdinh.demo.services.AuthenticationService;
import vn.omdinh.demo.services.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(
        UserService userService,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDTO signIn(LoginUserRequest loginUserRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginUserRequest.email(),
            loginUserRequest.password()
        ));

        return this.userService.getUserByEmail(loginUserRequest.email());
    }

    @Override
    public UserDTO signup(RegisterUserRequest registerUserRequest) {
        var userSignup = new UserDTO();

        userSignup.setEmail(registerUserRequest.email());
        userSignup.setPassword(passwordEncoder.encode(registerUserRequest.password()));

        return this.userService.save(userSignup);
    }
}
