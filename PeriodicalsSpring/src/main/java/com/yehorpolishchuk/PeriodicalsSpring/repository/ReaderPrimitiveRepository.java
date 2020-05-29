package com.yehorpolishchuk.PeriodicalsSpring.repository;

import com.yehorpolishchuk.PeriodicalsSpring.models.ReaderPrimitive;
import org.springframework.data.repository.CrudRepository;

public interface ReaderPrimitiveRepository extends CrudRepository<ReaderPrimitive, Integer> {
    ReaderPrimitive save(ReaderPrimitive r);
}
