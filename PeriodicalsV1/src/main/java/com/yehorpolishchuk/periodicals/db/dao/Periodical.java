package com.yehorpolishchuk.periodicals.db.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * frequencyPerYear should be set as a double value accordingly to the table
 * placed here https://en.wikipedia.org/wiki/Periodical_literature#Frequency
 *
 *
 * */
@Getter
@Setter
@NoArgsConstructor
public class Periodical {
    private String id;
    private String name;
    private double frequencyPerYear;
}
