package com.yehorpolishchuk.periodicals.db.dao;

/**
 * Country code should be set accordingly to ISO 3166-1 alpha-2.
 * Read more https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
 * */
public class Address {
    private String country;
    private String cityAreaOrDistrict;
    private String cityTownVillage;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String extraInfo;
    private String countryCode;
}
