package vn.omdinh.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.omdinh.demo.dtos.UserDTO;
import vn.omdinh.demo.models.requests.LoginUserRequest;
import vn.omdinh.demo.models.requests.RegisterUserRequest;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(
        UserService userService,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDTO signIn(LoginUserRequest loginUserRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginUserRequest.email(),
            loginUserRequest.password()
        ));

        return this.userService.getUserByEmail(loginUserRequest.email());
    }

    public UserDTO signup(RegisterUserRequest registerUserRequest) {
        var userSignup = new UserDTO();

        userSignup.setEmail(registerUserRequest.email());
        userSignup.setPassword(passwordEncoder.encode(registerUserRequest.password()));

        return this.userService.save(userSignup);
    }
}
