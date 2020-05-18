package com.yehorpolishchuk.periodicals.datastructures;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Subscription {
    private String readerId;
    private String periodicalId;
    private Date from;
    private int termInMonth;
    private boolean isPaid;
    private int quantity;
    private int paymentId;

    private Payment payment;
    private String id;
}
