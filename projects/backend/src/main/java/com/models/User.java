package com.models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String passwordHash;
    private boolean active = true;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nominee> nominees = new ArrayList<>();

    // constructors, getters, setters

    public User(){}
    public User(String email, String name, String passwordHash){ this.email = email; this.name = name; this.passwordHash = passwordHash; }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public String getPasswordHash(){ return passwordHash; }
    public void setPasswordHash(String passwordHash){ this.passwordHash = passwordHash; }
    public boolean isActive(){ return active; }
    public void setActive(boolean active){ this.active = active; }
    public List<Nominee> getNominees(){ return nominees; }
    public void setNominees(List<Nominee> nominees){ this.nominees = nominees; }
}