// package com.hms.backend.service.impl;

// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.hms.backend.dto.request.RegisterRequest;
// import com.hms.backend.dto.response.AuthResponse;
// import com.hms.backend.repository.RoleRepository;
// import com.hms.backend.repository.UserRepository;
// import com.hms.backend.service.AuthService;

// @Service
// public class AuthServiceImpl implements AuthService {
//     private final UserRepository userRepository;

//     private final RoleRepository roleRepository;

//     private final PasswordEncoder passwordEncoder;

//     public AuthServiceImpl(UserRepository userRepository,
//                        RoleRepository roleRepository,
//                        PasswordEncoder passwordEncoder) {

//     this.userRepository = userRepository;
//     this.roleRepository = roleRepository;
//     this.passwordEncoder = passwordEncoder;
//     }
//     @Override
// public AuthResponse register(RegisterRequest request) {

//     if (userRepository.existsByEmail(request.getEmail())) {
//         return new AuthResponse(
//                 null,
//                 null,
//                 null,
//                 "Email already exists"
//         );
//     }

//     if (userRepository.existsByPhone(request.getPhone())) {
//         return new AuthResponse(
//                 null,
//                 null,
//                 null,
//                 "Phone number already exists"
//         );
//     }

//     // Continue here...
// }
    
// }