package com.piranavan.ans_backend.service;

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
            return new Mobile();
        }
        return mobileRepository.save(mobile);
    }

    public List<Mobile> getAllMobiles() {
        return mobileRepository.findAll();
    }
}
