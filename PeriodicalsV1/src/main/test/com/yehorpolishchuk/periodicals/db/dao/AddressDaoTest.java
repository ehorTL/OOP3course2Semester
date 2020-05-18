package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.datastructures.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressDaoTest {

    private Address address = null;

    @Before
    public void setUp(){
        address = new Address();
        address.setCountryCode("UA");
        address.setCountry("Ukraine");
        address.setExtraInfo("extra info");
        address.setCityTownVillage("Kiev");
        address.setHouseNumber("156");
        address.setStreet("Superstar str");
        address.setCityAreaOrDistrict("Kyivska oblast");
        address.setPostalCode("55555");
    }

    @Test
    public void addGetDeleteTest(){
//        int key = AddressDao.addAddress(address);
//        AddressDao.getAddressById(key);
//        AddressDao.deleteAddressById(key);
    }
}