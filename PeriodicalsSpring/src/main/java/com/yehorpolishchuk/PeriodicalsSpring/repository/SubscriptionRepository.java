package com.yehorpolishchuk.PeriodicalsSpring.repository;

import com.yehorpolishchuk.PeriodicalsSpring.models.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>{
    Subscription save(Subscription s);
}