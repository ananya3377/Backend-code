package com.controller;

import  com.models.UnlockRequest;
import com.service.UnlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/unlock")
public class UnlockController {
    private final UnlockService unlockService;

    public UnlockController(UnlockService unlockService){ this.unlockService = unlockService; }

    @PostMapping("/request")
    public ResponseEntity<?> requestUnlock(@RequestParam Long requesterId, @RequestParam MultipartFile file) throws IOException {
        UnlockRequest r = unlockService.createRequest(requesterId, file);
        return ResponseEntity.ok(r);
    }

    @PostMapping("/approve/co/{id}")
    public ResponseEntity<?> approveByCo(@PathVariable Long id){
        UnlockRequest r = unlockService.approveByCoVerifier(id);
        return ResponseEntity.ok(r);
    }

    @PostMapping("/approve/admin/{id}")
    public ResponseEntity<?> approveByAdmin(@PathVariable Long id){
        UnlockRequest r = unlockService.adminApprove(id);
        return ResponseEntity.ok(r);
    }
}