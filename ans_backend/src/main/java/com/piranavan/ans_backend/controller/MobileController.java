package com.piranavan.ans_backend.controller;

import com.piranavan.ans_backend.exception.DuplicateModelException;
import com.piranavan.ans_backend.model.Mobile;
import com.piranavan.ans_backend.service.MobileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ans/api")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @PostMapping("/mobile")
    public ResponseEntity<?> addMobile(@Validated @RequestBody Mobile mobile){
        try {
            Mobile mobile1 = mobileService.addMobile(mobile);
            return new ResponseEntity<>(mobile1, HttpStatus.CREATED);
        }
        catch (DuplicateModelException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mobiles")
    public ResponseEntity<List<Mobile>> getAllMobiles() {
        return new ResponseEntity<>(mobileService.getAllMobiles(), HttpStatus.OK);
    }

    @GetMapping("/mobile/{mobileId}")
    public ResponseEntity<Mobile> getMobile(@PathVariable Long mobileId){

        Mobile mobile = mobileService.getMobileById(mobileId);

        if (mobile != null){
            return new ResponseEntity<>(mobile,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mobile/search")
    public ResponseEntity<List<Mobile>> searchMobiles(@RequestParam String keyword){
        List<Mobile> mobiles = mobileService.searchMobiles(keyword);
        return new ResponseEntity<>(mobiles, HttpStatus.OK);
    }

    @GetMapping("/mobile/search/price")
    public ResponseEntity<List<Mobile>> searchMobilesByPrice(@RequestParam Double minPrice, @RequestParam Double maxPrice){
        List<Mobile> mobiles = mobileService.searchMobilesByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(mobiles,HttpStatus.OK);
    }

    @PutMapping("/mobile/{mobileId}")
    public ResponseEntity<?> updateMobile(@PathVariable Long mobileId, @Valid @RequestBody Mobile mobile){
        try {
            Mobile updateMobile = mobileService.updateMobile(mobileId, mobile);
            return new ResponseEntity<>(updateMobile, HttpStatus.OK);
        } catch (DuplicateModelException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/mobile/{mobileId}")
    public ResponseEntity<String> deleteMobile(@PathVariable Long mobileId){
        Mobile mobile = mobileService.getMobileById(mobileId);
        if (mobile != null) {
            mobileService.deleteMobile(mobileId);
            return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>("Mobile is not found",HttpStatus.NOT_FOUND);
        }
    }
}
