package com.yehorpolishchuk.periodicals.db.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Reader {
    private String id;
    private String firstName;
    private String LastName;
    private Address address;
    private ContactInfo contactInfo;
}
