package com.amudy.global.security.repository;

import com.amudy.global.security.domain.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaPersistentRepository extends JpaRepository<PersistentLogins, String> {
    Optional<PersistentLogins> findBySeries(String series);
    List<PersistentLogins> findByUsername(String username);
}
