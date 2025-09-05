package com.models;

import jakarta.persistence.*;
import java.time.*;

@Entity
public class UnlockRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User requester; // nominee (or guest) who requested

    private String status; // PENDING, VERIFIED, APPROVED, REJECTED
    private LocalDateTime createdAt = LocalDateTime.now();
    private String uploadedDocPath; // e.g., death certificate
    private boolean coVerifierApproved = false;

    public UnlockRequest(){}

    // getters/setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public User getRequester(){ return requester; }
    public void setRequester(User requester){ this.requester = requester; }
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }
    public LocalDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }
    public String getUploadedDocPath(){ return uploadedDocPath; }
    public void setUploadedDocPath(String uploadedDocPath){ this.uploadedDocPath = uploadedDocPath; }
    public boolean isCoVerifierApproved(){ return coVerifierApproved; }
    public void setCoVerifierApproved(boolean coVerifierApproved){ this.coVerifierApproved = coVerifierApproved; }
}
