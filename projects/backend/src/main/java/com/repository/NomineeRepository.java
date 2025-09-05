package com.repository;

import com.models.Nominee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NomineeRepository extends JpaRepository<Nominee, Long> {
}