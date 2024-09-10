package com.piranavan.ans_backend.controller;

import com.piranavan.ans_backend.exception.DuplicateModelException;
import com.piranavan.ans_backend.model.Tablet;
import com.piranavan.ans_backend.service.TabletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ans/api")
public class TabletController {

    @Autowired
    private TabletService tabletService;

    @PostMapping("/tab")
    public ResponseEntity<?> addTablet(@Validated @RequestBody Tablet tablet){
        try {
            Tablet tablet1 = tabletService.addTablet(tablet);
            return new ResponseEntity<>(tablet1, HttpStatus.CREATED);
        }
        catch (DuplicateModelException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tabs")
    public ResponseEntity<List<Tablet>> getAllTablet() {
        return new ResponseEntity<>(tabletService.getAllTablet(), HttpStatus.OK);
    }

    @GetMapping("/tab/{tabId}")
    public ResponseEntity<Tablet> getTablet(@PathVariable Long tabId){

        Tablet tablet = tabletService.getTabletById(tabId);

        if (tablet != null){
            return new ResponseEntity<>(tablet,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tab/search")
    public ResponseEntity<List<Tablet>> searchTablets(@RequestParam String keyword){
        List<Tablet> tablets = tabletService.searchTablets(keyword);
        return new ResponseEntity<>(tablets,HttpStatus.OK);
    }

    @GetMapping("/tab/search/price")
    public ResponseEntity<List<Tablet>> searchTabletByPrice(@RequestParam Double minPrice, @RequestParam Double maxPrice){
        List<Tablet> tablets = tabletService.searchTabletsByPrice(minPrice,maxPrice);
        return new ResponseEntity<>(tablets,HttpStatus.OK);
    }

    @PutMapping("/tab/{tabId}")
    public ResponseEntity<?> updateTablet(@PathVariable Long tabId, @Valid @RequestBody Tablet tablet){
        try {
            Tablet updateTablet = tabletService.updateTablet(tabId, tablet);
            return new ResponseEntity<>(updateTablet, HttpStatus.OK);
        } catch (DuplicateModelException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tab/{tabId}")
    public ResponseEntity<String> deleteTablet(@PathVariable Long tabId){
        Tablet tablet = tabletService.getTabletById(tabId);
        if (tablet != null){
            tabletService.deleteTablet(tabId);
            return new ResponseEntity<>("Delete", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Tablet is not found",HttpStatus.NOT_FOUND);
        }
    }
}
