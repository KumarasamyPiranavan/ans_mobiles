package com.piranavan.ans_backend.repository;

import com.piranavan.ans_backend.model.Mobile;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MobileRepository extends JpaRepository<Mobile,Long> {
    Optional<Mobile> findByModel(String model);

    Optional<Mobile> findById(Long mobileId);

    @Query("SELECT m FROM Mobile m WHERE m.name LIKE %?1% OR m.brand LIKE %?1% OR m.model LIKE %?1%")
    List<Mobile> searchMobile(String keyword);

    @Query("SELECT m FROM Mobile m WHERE m.price BETWEEN ?1 AND ?2")
    List<Mobile> searchMobilePrice(Double minPrice, Double maxPrice);
}
