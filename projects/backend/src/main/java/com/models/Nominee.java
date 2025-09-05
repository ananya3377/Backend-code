package com.models;

import jakarta.persistence.*;

@Entity
public class Nominee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String role; // NOMINEE / CO_VERIFIER
    private String permissionTier; // FULL / LIMITED

    @ManyToOne
    private User owner;

    public Nominee(){}
    public Nominee(String name, String email, String role, String permissionTier){ this.name=name;this.email=email;this.role=role;this.permissionTier=permissionTier; }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    public String getRole(){ return role; }
    public void setRole(String role){ this.role = role; }
    public String getPermissionTier(){ return permissionTier; }
    public void setPermissionTier(String permissionTier){ this.permissionTier = permissionTier; }
    public User getOwner(){ return owner; }
    public void setOwner(User owner){ this.owner = owner; }
}