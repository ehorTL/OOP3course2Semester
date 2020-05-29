package com.yehorpolishchuk.PeriodicalsSpring.models;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContactInfo {
    private ArrayList<String> numbers;
    private ArrayList<String> mailAddresses;
    private int readerId;
}
