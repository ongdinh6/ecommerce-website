package vn.omdinh.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.omdinh.demo.dtos.ProductDTO;
import vn.omdinh.demo.dtos.UserDTO;
import vn.omdinh.demo.exceptions.ExceptionResponse;
import vn.omdinh.demo.models.requests.LoginUserRequest;
import vn.omdinh.demo.models.requests.RegisterUserRequest;
import vn.omdinh.demo.models.responses.LoginUserResponse;
import vn.omdinh.demo.services.AuthenticationService;
import vn.omdinh.demo.services.JwtService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService
    ) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Register user")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Create success",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid request body format",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<UserDTO> doSignUp(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        var userSignup = this.authenticationService.signup(registerUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSignup);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginUserResponse.class)) }
            ),
            @ApiResponse(responseCode = "500",
                description = "Internal server error",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)) }
            ),
        }
    )
    ResponseEntity<LoginUserResponse> doSignIn(@RequestBody LoginUserRequest loginUserRequest) {
        var userSignIn = this.authenticationService.signIn(loginUserRequest);

        var token = this.jwtService.generateToken(userSignIn);
        var expiresIn = this.jwtService.expiredTime(token);

        return ResponseEntity.ok().body(new LoginUserResponse(token, expiresIn));
    }

}
