package com.cet.collaborative_editing_tool.repository;

import com.cet.collaborative_editing_tool.enums.ERoles;
import com.cet.collaborative_editing_tool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByRole(ERoles role);
}
