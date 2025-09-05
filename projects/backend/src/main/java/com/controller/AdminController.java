package com.estatevault.controller;

import com.estatevault.model.UnlockRequest;
import com.estatevault.repository.UnlockRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UnlockRequestRepository unlockRepo;

    public AdminController(UnlockRequestRepository unlockRepo){ this.unlockRepo = unlockRepo; }

    @GetMapping("/unlock-requests")
    public ResponseEntity<List<UnlockRequest>> listPending(){
        return ResponseEntity.ok(unlockRepo.findByStatus("PENDING"));
    }
}