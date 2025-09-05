package com.controller;

import com.models.User;
import com.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder){ this.userRepo = userRepo; this.passwordEncoder = passwordEncoder; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User dto){
        if(userRepo.findByEmail(dto.getEmail()).isPresent()) return ResponseEntity.badRequest().body("Email already registered");
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setName(dto.getName());
        u.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        User saved = userRepo.save(u);
        return ResponseEntity.ok(saved);
    }
}