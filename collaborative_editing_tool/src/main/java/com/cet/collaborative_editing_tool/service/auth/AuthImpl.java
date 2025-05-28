package com.cet.collaborative_editing_tool.service.auth;

import com.cet.collaborative_editing_tool.dto.RegisterRequest;
import com.cet.collaborative_editing_tool.dto.UserDto;
import com.cet.collaborative_editing_tool.enums.ERoles;
import com.cet.collaborative_editing_tool.model.User;
import com.cet.collaborative_editing_tool.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthImpl implements AuthService{
    private final UserRepository userRepository;

    public AuthImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAnAdminAccount() {
        Optional<User> adminAccount = userRepository.findByRole(ERoles.ADMIN);
        if(adminAccount.isEmpty()) {

            User user = new User();
            user.setEmail("mahingarodin@gmail.com");
            user.setName("Rodin");
            user.setRole(ERoles.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");

        } else {
            System.out.println("Admin account already exist");
        }
    }

    @Override
    public UserDto createUser(RegisterRequest request) {
    if(userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new EntityExistsException("User already exists with email: " + request.getEmail());
    }

    User user = new User();
    user.setEmail(request.getEmail());
    user.setName(request.getName());
    user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
    user.setRole(ERoles.VIEWER);
    User savedUser = userRepository.save(user);
    return savedUser.getUserDto();
    }
}
