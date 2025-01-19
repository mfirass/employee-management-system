package dev.mfirass.employee_management_system.core.security.controller;

import dev.mfirass.employee_management_system.core.security.dto.LoginRequest;
import dev.mfirass.employee_management_system.core.security.model.Role;
import dev.mfirass.employee_management_system.core.security.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        var principal = SecurityContextHolder.getContext().getAuthentication();

        User connectedUser = new User();
        connectedUser.setEmail(principal.getName());
        connectedUser.setRole(Role.valueOf(principal.getAuthorities().stream().findFirst().get().getAuthority().substring(5)));
        return ResponseEntity.ok().body(connectedUser);
    }
}