package com.estatevault.repository;

import com.estatevault.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByOwnerId(Long ownerId);
}

---
// src/main/java/com/estatevault/repository/UnlockRequestRepository.java
        package com.estatevault.repository;

import com.estatevault.model.UnlockRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnlockRequestRepository extends JpaRepository<UnlockRequest, Long> {
    List<UnlockRequest> findByStatus(String status);
}