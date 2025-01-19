package dev.mfirass.employee_management_system.core.security.repository;

import dev.mfirass.employee_management_system.core.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}