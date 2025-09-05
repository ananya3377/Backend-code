package com.repository;

import com.models.UnlockRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnlockRequestRepository extends JpaRepository<UnlockRequest, Long> {
    List<UnlockRequest> findByStatus(String status);
}