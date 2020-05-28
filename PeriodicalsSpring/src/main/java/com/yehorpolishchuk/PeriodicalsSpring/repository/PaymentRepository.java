package com.yehorpolishchuk.PeriodicalsSpring.repository;

import com.yehorpolishchuk.PeriodicalsSpring.models.Payment;
import com.yehorpolishchuk.PeriodicalsSpring.models.Periodical;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Payment save(Payment p);
}
