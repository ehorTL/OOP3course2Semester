package com.yehorpolishchuk.PeriodicalsSpring.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name="address")
public class Address {

    @Id
    @Column(name="aid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="country")
    private String country;

    @Column(name="countrycode")
    private String countryCode;

    @Column(name="cityareaordistrict")
    private String cityAreaOrDistrict;

    @Column(name="citytownvillage")
    private String cityTownVillage;

    @Column(name="street")
    private String street;

    @Column(name="housenumber")
    private String houseNumber;

    @Column(name="postalcode")
    private String postalCode;

    @Column(name="extrainfo")
    private String extraInfo;
}
