package dev.Innocent.repository;

import dev.Innocent.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Page<Property> findByUserId(Long userId, Pageable pageable);
    Optional<Property> findById(Long id);
}