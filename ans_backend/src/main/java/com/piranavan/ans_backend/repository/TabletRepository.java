package com.piranavan.ans_backend.repository;

import com.piranavan.ans_backend.model.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabletRepository extends JpaRepository<Tablet,Long> {
   Optional<Tablet> findByModel(String model);

   @Query("SELECT m FROM Tablet m WHERE m.name Like %?1% OR m.brand LIKE %?1% OR m.model LIKE %?1%")
   List<Tablet> searchTablet(String keyword);

   @Query("SELECT m FROM Tablet m WHERE m.price BETWEEN ?1 AND ?2")
   List<Tablet> searchTabletsPrice(Double minPrice, Double maxPrice);
}
