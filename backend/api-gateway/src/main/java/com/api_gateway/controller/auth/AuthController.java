package com.api_gateway.controller.auth;

import com.api_gateway.dto.rest.SignInResponse;
import com.api_gateway.model.user.User;
import com.api_gateway.service.auth.AuthService;
import com.api_gateway.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @GetMapping("/type")
    public User getSignInType() {
        return authService.getUserTypeOnly();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signInUser(@RequestBody User input) {
        User signInUser = authService.authenticateUser(input);
        String jwtToken = jwtService.generateToken(signInUser);

        SignInResponse signInResponse = new SignInResponse(jwtToken,
                                                           signInUser.getUsername(),
                                                           signInUser.getUserType(),
                                                           signInUser.getRollNumber());
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping("/sign-up")
    public User signIn(@RequestBody User user) {
        System.out.println(user);
        return authService.signup(user);
    }

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    @GetMapping("/delete")
    public void deleteALl() {
        authService.clearUsers();
    }
}
