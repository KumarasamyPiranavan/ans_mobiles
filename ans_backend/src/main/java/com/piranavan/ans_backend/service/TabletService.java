package com.piranavan.ans_backend.service;

import com.piranavan.ans_backend.exception.DuplicateModelException;
import com.piranavan.ans_backend.model.Tablet;
import com.piranavan.ans_backend.repository.TabletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabletService {

    @Autowired
    private TabletRepository tabletRepository;

    public Tablet addTablet(Tablet tablet) {
        Optional<Tablet> existingTablet = tabletRepository.findByModel(tablet.getModel());
        if (existingTablet.isPresent()){
            throw new DuplicateModelException("A mobile with this model already exists");
        }
        return tabletRepository.save(tablet);
    }

    public List<Tablet> getAllTablet() {
        return tabletRepository.findAll();
    }

    public Tablet getTabletById(Long tabId) {
        return tabletRepository.findById(tabId).orElse(null);
    }

    public List<Tablet> searchTablets(String keyword) {
        return tabletRepository.searchTablet(keyword);
    }

    public List<Tablet> searchTabletsByPrice(Double minPrice, Double maxPrice) {
        return tabletRepository.searchTabletsPrice(minPrice,maxPrice);
    }

    public Tablet updateTablet(Long tabId, Tablet tablet) {
        Tablet tablet1 = tabletRepository.findById(tabId).orElseThrow(() -> new RuntimeException("Tablet is not found"));

        Optional<Tablet> existingTablet = tabletRepository.findByModel(tablet.getModel());
        if (existingTablet.isPresent() && !existingTablet.get().getTabId().equals(tabId)){
            throw new DuplicateModelException("A Tablet with this model already exists");
        } else {
            tablet1.setName(tablet.getName());
            tablet1.setBrand(tablet.getBrand());
            tablet1.setModel(tablet.getModel());
            tablet1.setReleaseDate(tablet.getReleaseDate());
            tablet1.setPrice(tablet.getPrice());

            return tabletRepository.save(tablet1);
        }
    }

    public void deleteTablet(Long tabId) {
        tabletRepository.deleteById(tabId);
    }
}
