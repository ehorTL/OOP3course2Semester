package com.yehorpolishchuk.PeriodicalsSpring.repository;

import com.yehorpolishchuk.PeriodicalsSpring.models.Address;
import com.yehorpolishchuk.PeriodicalsSpring.models.Periodical;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    public Address save(Address address);
}
