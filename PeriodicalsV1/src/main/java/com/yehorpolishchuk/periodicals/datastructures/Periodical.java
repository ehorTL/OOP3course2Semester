package com.yehorpolishchuk.periodicals.datastructures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * frequencyPerYear should be set as a double value accordingly to the table
 * placed here https://en.wikipedia.org/wiki/Periodical_literature#Frequency
 *
 *
 * */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Periodical {
    private String id;
    private String name;
    private double frequencyPerYear;
//    private String type;
//    private String genre;
}
