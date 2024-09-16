package com.piranavan.ans_backend.controller;

import com.piranavan.ans_backend.exception.DuplicateModelException;
import com.piranavan.ans_backend.model.Accessories;
import com.piranavan.ans_backend.service.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ans/api")
public class AccessoriesController {

    @Autowired
    private AccessoriesService accessoriesService;

    @PostMapping("/accessories")
    public ResponseEntity<?> addAccessories(@Validated @RequestBody Accessories accessories) {
        try {
            Accessories accessories1 = accessoriesService.addAccessories(accessories);
            return new ResponseEntity<>(accessories1, HttpStatus.CREATED);
        }
        catch (DuplicateModelException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accessories")
    public ResponseEntity<List<Accessories>> getAllAccessories() {
        return new ResponseEntity<>(accessoriesService.getAllAccessories(), HttpStatus.OK);
    }

    @GetMapping("/accessories/{accessoriesId}")
    public ResponseEntity<Accessories> getAccessories(@PathVariable Long accessoriesId){
        Accessories accessories = accessoriesService.getAccessoriesId(accessoriesId);

        if (accessories != null){
            return new ResponseEntity<>(accessories,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
