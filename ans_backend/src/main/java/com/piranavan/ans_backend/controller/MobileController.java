package com.piranavan.ans_backend.controller;

import com.piranavan.ans_backend.model.Mobile;
import com.piranavan.ans_backend.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ans/api")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @PostMapping("/mobile")
    public ResponseEntity<?> addMobile(@RequestBody Mobile mobile){
        try {
            Mobile mobile1 = mobileService.addMobile(mobile);
            return new ResponseEntity<>(mobile1, HttpStatus.CREATED);
        }
        catch (Exception e){
            if (e.getMessage().contains("A mobile with this model already exists")){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mobiles")
    public ResponseEntity<List<Mobile>> getAllMobiles() {
        return new ResponseEntity<>(mobileService.getAllMobiles(), HttpStatus.OK);
    }
}
