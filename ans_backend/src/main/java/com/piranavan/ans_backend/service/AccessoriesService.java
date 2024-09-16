package com.piranavan.ans_backend.service;

import com.piranavan.ans_backend.model.Accessories;
import com.piranavan.ans_backend.repository.AccessoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoriesService {

    @Autowired
    private AccessoriesRepository accessoriesRepository;

    public Accessories addAccessories(Accessories accessories) {
        Optional<Accessories> existingAccessories = accessoriesRepository.findByModel(accessories.getModel());
        if (existingAccessories.isPresent()) {
            throw new RuntimeException("A accessories with this model already exists");
        }
        return accessoriesRepository.save(accessories);
    }

    public List<Accessories> getAllAccessories() {
        return accessoriesRepository.findAll();
    }

    public Accessories getAccessoriesId(Long accessoriesId) {
        return accessoriesRepository.findById(accessoriesId).orElse(null);
    }
}
