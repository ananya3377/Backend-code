package com.models;

import jakarta.persistence.*;
import java.time.*;
import com.models.User;

@Entity
public class Document {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String contentType;
    private String storagePath;
    private LocalDateTime uploadedAt = LocalDateTime.now();
    private String category; // WILL, POLICY, DEED, etc

    @ManyToOne
    private User owner;

    public Document(){}

    // getters/setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getFilename(){ return filename; }
    public void setFilename(String filename){ this.filename = filename; }
    public String getContentType(){ return contentType; }
    public void setContentType(String contentType){ this.contentType = contentType; }
    public String getStoragePath(){ return storagePath; }
    public void setStoragePath(String storagePath){ this.storagePath = storagePath; }
    public LocalDateTime getUploadedAt(){ return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt){ this.uploadedAt = uploadedAt; }
    public String getCategory(){ return category; }
    public void setCategory(String category){ this.category = category; }
    public User getOwner(){ return owner; }
    public void setOwner(User owner){ this.owner = owner; }
}