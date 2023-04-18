package com.hendisantika.springbootjwtdockerpostgresql.controller;

import com.hendisantika.springbootjwtdockerpostgresql.jwt.JwtUtils;
import com.hendisantika.springbootjwtdockerpostgresql.model.User;
import com.hendisantika.springbootjwtdockerpostgresql.payload.request.SignupRequest;
import com.hendisantika.springbootjwtdockerpostgresql.payload.response.MessageResponse;
import com.hendisantika.springbootjwtdockerpostgresql.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-docker-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/23
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "User", description = "Endpoints for managing user")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    @Operation(
            summary = "Sign Up new User",
            description = "Sign Up new User.",
            tags = {"User"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            User.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not Authorize", responseCode = "403",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone Number is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getPhoneNumber(), signUpRequest.getName(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
