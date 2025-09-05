package com.estate-vault.applications.controller;

import com.estatevault.model.Nominee;
import com.estatevault.model.User;
import com.estatevault.repository.NomineeRepository;
import com.estatevault.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nominees")
public class NomineeController {
    private final NomineeRepository nomineeRepo;
    private final UserRepository userRepo;

    public NomineeController(NomineeRepository nomineeRepo, UserRepository userRepo){ this.nomineeRepo = nomineeRepo; this.userRepo = userRepo; }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> addNominee(@PathVariable Long userId, @RequestBody Nominee n){
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        n.setOwner(u);
        Nominee saved = nomineeRepo.save(n);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Nominee>> listForUser(@PathVariable Long userId){
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return ResponseEntity.ok(u.getNominees());
    }
}