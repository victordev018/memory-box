package com.jota.memorybox.repository;

import com.jota.memorybox.model.PostMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostMemoryRepository extends JpaRepository<PostMemory, Long> {
    Optional<PostMemory> findByName(String name);
}
