package com.controller;

import  com.models.Document;
import  com.models.User;
import com.repository.DocumentRepository;
import com.repository.UserRepository;
import com.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final StorageService storageService;
    private final DocumentRepository documentRepo;
    private final UserRepository userRepo;

    public DocumentController(StorageService storageService, DocumentRepository documentRepo, UserRepository userRepo){
        this.storageService = storageService; this.documentRepo = documentRepo; this.userRepo = userRepo;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam Long ownerId, @RequestParam MultipartFile file, @RequestParam(required=false) String category) throws IOException {
        User owner = userRepo.findById(ownerId).orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        String path = storageService.store(file, "documents");
        Document d = new Document();
        d.setFilename(file.getOriginalFilename());
        d.setContentType(file.getContentType());
        d.setStoragePath(path);
        d.setCategory(category == null ? "UNCATEGORIZED" : category);
        d.setOwner(owner);
        Document saved = documentRepo.save(d);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Document>> listForOwner(@PathVariable Long ownerId){
        return ResponseEntity.ok(documentRepo.findByOwnerId(ownerId));
    }
}