package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.datastructures.Address;
import com.yehorpolishchuk.periodicals.datastructures.ContactInfo;
import com.yehorpolishchuk.periodicals.datastructures.Reader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReaderDaoTest {

    private Reader reader = null;
    private Address address = null;
    private ContactInfo contactInfo = null;

    @Before
    public void setUp() throws Exception {
        address = new Address();
        address.setCountryCode("US");
        address.setCountry("USA");
        address.setExtraInfo("extra info");
        address.setCityTownVillage("Washington2");
        address.setHouseNumber("156");
        address.setStreet("Superstar str");
        address.setCityAreaOrDistrict("Washington state");
        address.setPostalCode("22222");

        contactInfo = new ContactInfo();
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<String> mails = new ArrayList<>();
        numbers.add("09876543321");
        numbers.add("09999999999");
        mails.add("bush@gmail.com");
        mails.add("db@gmail.com");
        contactInfo.setNumbers(numbers);
        contactInfo.setMailAddresses(mails);

        reader = new Reader();
        reader.setFirstName("George");
        reader.setLastName("Bush");
        reader.setAddress(address);
        reader.setContactInfo(contactInfo);
    }

    @Test
    public void addGetReaderTest(){
//        ReaderDao.addReader(reader);
    }
}