package com.hms.backend.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.backend.dto.request.LoginRequest;
import com.hms.backend.dto.request.RegisterRequest;
import com.hms.backend.dto.response.AuthResponse;
import com.hms.backend.entity.Role;
import com.hms.backend.entity.RoleName;
import com.hms.backend.entity.User;
import com.hms.backend.repository.RoleRepository;
import com.hms.backend.repository.UserRepository;
import com.hms.backend.service.AuthService;
import com.hms.backend.security.JwtService;     

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(
                    null,
                    null,
                    null,
                    "Email already exists"
            );
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            return new AuthResponse(
                    null,
                    null,
                    null,
                    "Phone number already exists"
            );
        }

        Role role = roleRepository.findByName(
                RoleName.valueOf(request.getRole().toUpperCase())
        ).orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(role);

        userRepository.save(user);

        return new AuthResponse(
                null,
                role.getName().name(),
                user.getFirstName(),
                "Registration successful"
        );
    }
        @Override
        public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getRole().getName().name(),
                user.getFirstName(),
                "Login successful"
        );
        }
}