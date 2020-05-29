package com.yehorpolishchuk.PeriodicalsSpring.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name="subscriptionper")
public class Subscription implements Serializable {
    @Id
    @Column(name="sid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="readerid")
    private int readerId;

    @Column(name="periodicalid")
    private int periodicalId;

    @Column(name="fromdate")
    private Date from;

    @Column(name="terminmonth")
    private int termInMonth;

    @Column(name="ispaid")
    private boolean isPaid;

    @Column(name="quantity")
    private int quantity;

    @Column(name="paymentid")
    private int paymentId;

    @Transient
    private Payment payment;
}

