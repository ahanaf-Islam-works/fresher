package com.api_gateway.service.auth;

import com.api_gateway.exception.ApiException;
import com.api_gateway.model.explorer.Explorer;
import com.api_gateway.model.producer.Producer;
import com.api_gateway.model.user.User;
import com.api_gateway.model.user.UserType;
import com.api_gateway.repository.UserRepository;
import com.api_gateway.service.producerService.RestProducer;
import com.api_gateway.utils.GenerateItems;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RestProducer restProducer;

    @Transactional
    public User signup(User input) {
        if (userRepository.findByEmail(input.getEmail()).isPresent() || userRepository.findByRollNumber(input.getRollNumber()).isPresent()) {
            throw new ApiException("Email or roll number already in use. Please choose a different one.", HttpStatus.BAD_REQUEST);
        }
        User newUser = User.builder()
                .userName(input.getUsername())
                .email(input.getEmail())
                .rollNumber(input.getRollNumber())
                .userType(input.getUserType())
                .password(passwordEncoder.encode(input.getPassword()))
                .active(false)
                .token(GenerateItems.generateRandomString(20))
                .build();
        User createdUser = userRepository.save(newUser);

        if (createdUser.getUserType() == UserType.PRODUCER) {
            Producer newProducer = Producer.builder().userName(createdUser.getUsername())
                    .rollNumber(createdUser.getRollNumber())
                    .build();
            String result = restProducer.createProducer(newProducer);
            if (result != null) {
                return createdUser;
            } else {
                throw new ApiException("Failed to create producer", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            Explorer newExplorer = Explorer.builder()
                    .userName(createdUser.getUsername())
                    .rollNumber(createdUser.getRollNumber())
                    .build();
        }

        return createdUser;
    }

    public User authenticateUser(User input) {

        boolean authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(), input.getPassword()
                )
        ).isAuthenticated();
        if (authentication) {
            return userRepository.findByEmail(input.getEmail())
                    .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        } else {
            throw new ApiException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void clearUsers() {
        userRepository.deleteAll();
    }

    public User getUserTypeOnly(){
        return User.builder()
                .build();

    }

    public String testRestClient(){
        return restProducer.getProducerTest();
    }
}
