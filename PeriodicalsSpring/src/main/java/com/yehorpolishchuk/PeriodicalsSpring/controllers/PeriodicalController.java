package com.yehorpolishchuk.PeriodicalsSpring.controllers;

import com.yehorpolishchuk.PeriodicalsSpring.models.Periodical;
import com.yehorpolishchuk.PeriodicalsSpring.services.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "periodical", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class PeriodicalController {

    @Autowired
    private PeriodicalService periodicalService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Periodical>> getAllPeriodicals() {
        List<Periodical> periodicals = periodicalService.getAllPeriodicals();
        if(periodicals.size() > 0) {
            return new ResponseEntity<List<Periodical>>(periodicals, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Periodical>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/admin/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Periodical> addPeriodical(@RequestBody Periodical periodical) {
        try {
            return new ResponseEntity<Periodical>(periodicalService.addPeriodical(periodical), HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e) {
            return new ResponseEntity<Periodical>(HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            return new ResponseEntity<Periodical>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Periodical> getPeriodical(@PathVariable Integer id) {
        Optional<Periodical> p = periodicalService.getById(id);
        if(p.isPresent()) {
            return new ResponseEntity<Periodical>(p.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Periodical>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/admin/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Periodical> editPeriodical(@RequestBody Periodical periodical){
        try {
            return new ResponseEntity<Periodical>(periodicalService.update(periodical), HttpStatus.OK);
        }
        catch(IllegalArgumentException e) {
            return new ResponseEntity<Periodical>(HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            return new ResponseEntity<Periodical>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete/{id}")
    public void deletePeriodical(@PathVariable int id){
        periodicalService.deleteById(id);
    }
}
