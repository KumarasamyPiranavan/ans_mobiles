package com.piranavan.ans_backend.service;

import com.piranavan.ans_backend.exception.DuplicateModelException;
import com.piranavan.ans_backend.model.Mobile;
import com.piranavan.ans_backend.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobileService {

    @Autowired
    private MobileRepository mobileRepository;

    public Mobile addMobile(Mobile mobile) {
        Optional<Mobile> existingMobile = mobileRepository.findByModel(mobile.getModel());
        if (existingMobile.isPresent()){
            throw new DuplicateModelException("A mobile with this model already exists");
        }
        return mobileRepository.save(mobile);
    }

    public List<Mobile> getAllMobiles() {
        return mobileRepository.findAll();
    }

    public Mobile getMobileById(Long mobileId) {
        return mobileRepository.findById(mobileId).orElse(null);
    }

    public List<Mobile> searchMobiles(String keyword) {
        return mobileRepository.searchMobile(keyword);
    }


    public List<Mobile> searchMobilesByPrice(Double minPrice, Double maxPrice) {
        return mobileRepository.searchMobilePrice(minPrice, maxPrice);
    }
}
