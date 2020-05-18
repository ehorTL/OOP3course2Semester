package com.yehorpolishchuk.periodicals.datastructures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represent post address (physical address).
 * <p>
 * Country code should be set accordingly to ISO 3166-1 alpha-2.
 * Read more https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    private String id;
    private String country;
    private String countryCode;
    private String cityAreaOrDistrict;
    private String cityTownVillage;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String extraInfo;
}
