package com.yehorpolishchuk.PeriodicalsSpring.controllers;

import com.yehorpolishchuk.PeriodicalsSpring.models.SubscribeUtil;
import com.yehorpolishchuk.PeriodicalsSpring.services.PeriodicalService;
import com.yehorpolishchuk.PeriodicalsSpring.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "subscription", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class SubscriptionController {

    @Autowired
    private PeriodicalService periodicalService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping(path = "/price/{pid}/{termInMonth}")
    public ResponseEntity<Double> getPeriodical(@PathVariable Integer pid, @PathVariable Integer termInMonth) {
        double priceCalculated = subscriptionService.calculatePriceOfSubscription(pid, termInMonth);
        if (Double.compare(priceCalculated, 0.0) <= 0){
            return new ResponseEntity<Double>(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<Double>(priceCalculated, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/add/full", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscribeUtil> addSubscription(@RequestBody SubscribeUtil subscribeUtil) {
        Optional<SubscribeUtil> su = subscriptionService.addSubscription(subscribeUtil);
        if(su.isPresent()) {
            return new ResponseEntity<SubscribeUtil>(su.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<SubscribeUtil>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
