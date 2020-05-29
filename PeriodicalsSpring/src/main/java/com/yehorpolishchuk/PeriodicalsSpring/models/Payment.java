package com.yehorpolishchuk.PeriodicalsSpring.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Data
@Table(name="payment")
public class Payment {

    @Id
    @Column(name="pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "toaccount")
    private String transferredToAccount;

    @Column(name = "sum")
    private double sum;

    @Column(name = "currencycode")
    private String currencyCode;

    @Column(name = "paymentdatetime")
    private LocalDateTime paymentDateTime;

    @Column(name = "description")
    private String description;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "rid", insertable = false, updatable = false, referencedColumnName = "rid")
    private Reader payer;

    @Column(name = "rid")
    private int readerId;

    @Column(name = "fromaccount")
    private String fromAccount;
}
