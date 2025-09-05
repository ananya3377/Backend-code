package com.estatevault.repository;

import com.estatevault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

---
// src/main/java/com/estatevault/repository/NomineeRepository.java
        package com.estatevault.repository;

import com.estatevault.model.Nominee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NomineeRepository extends JpaRepository<Nominee, Long> {
}