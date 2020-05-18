package com.yehorpolishchuk.periodicals.datastructures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Represent Reader contact info such as mobile numbers
 * and e-mail addressed.
 * */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContactInfo {
    private ArrayList<String> numbers;
    private ArrayList<String> mailAddresses;
    private int readerId;
}
