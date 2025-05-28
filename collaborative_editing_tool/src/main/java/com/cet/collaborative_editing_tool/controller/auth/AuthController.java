package com.cet.collaborative_editing_tool.controller.auth;

import com.cet.collaborative_editing_tool.dto.AuthenticationRequest;
import com.cet.collaborative_editing_tool.dto.AuthenticationResponse;
import com.cet.collaborative_editing_tool.dto.RegisterRequest;
import com.cet.collaborative_editing_tool.dto.UserDto;
import com.cet.collaborative_editing_tool.model.User;
import com.cet.collaborative_editing_tool.repository.UserRepository;
import com.cet.collaborative_editing_tool.security.JwtUtil;
import com.cet.collaborative_editing_tool.security.UserService;
import com.cet.collaborative_editing_tool.service.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v3/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private  final UserService userService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil, UserService userService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signupUser(@RequestBody RegisterRequest request) {
        try {
            UserDto createdUser = authService.createUser(request);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistsException) {
            return new ResponseEntity<>("User Already Exists!", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("User Not Created. Please try again later", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw  new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());

        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if(optionalUser.isPresent()) {
            authenticationResponse.setToken(jwt);
            authenticationResponse.setRole(optionalUser.get().getRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return ResponseEntity.ok(authenticationResponse).getBody();
    }

}
