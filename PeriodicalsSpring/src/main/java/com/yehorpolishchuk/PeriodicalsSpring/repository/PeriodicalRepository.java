package com.yehorpolishchuk.PeriodicalsSpring.repository;

import com.yehorpolishchuk.PeriodicalsSpring.models.Periodical;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PeriodicalRepository extends CrudRepository<Periodical, Integer> {
    Optional<Periodical> findById(int id);
    List<Periodical> findAll();
}

