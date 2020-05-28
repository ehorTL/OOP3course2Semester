package com.yehorpolishchuk.PeriodicalsSpring.services;

import com.yehorpolishchuk.PeriodicalsSpring.models.Periodical;
import com.yehorpolishchuk.PeriodicalsSpring.repository.PeriodicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodicalService {

    @Autowired
    private PeriodicalRepository periodicalRepository;

    public List<Periodical> getAllPeriodicals() {
        return periodicalRepository.findAll();
    }

    public Periodical addPeriodical(Periodical p){
        return periodicalRepository.save(p);
    }

    public void deleteById(int id){
        periodicalRepository.deleteById(id);
    }

    public Periodical update(Periodical p){
        return periodicalRepository.save(p);
    }

    public Optional<Periodical> getById(int id){
        return periodicalRepository.findById(id);
    }

}
