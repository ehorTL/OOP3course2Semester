package com.yehorpolishchuk.periodicals.datastructures;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reader {
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private ContactInfo contactInfo;
}
