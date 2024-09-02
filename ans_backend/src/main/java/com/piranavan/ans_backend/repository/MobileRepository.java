package com.piranavan.ans_backend.repository;

import com.piranavan.ans_backend.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileRepository extends JpaRepository<Mobile,Long> {
    Optional<Mobile> findByModel(String model);
}
