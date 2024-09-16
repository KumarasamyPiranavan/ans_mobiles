package com.piranavan.ans_backend.repository;

import com.piranavan.ans_backend.model.Accessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessoriesRepository extends JpaRepository<Accessories,Long> {

    Optional<Accessories> findByModel(String model);
}
