package com.yehorpolishchuk.PeriodicalsSpring.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name="periodical")
public class Periodical implements Serializable {
    @Id
    @Column(name="pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name="frequency")
    private double frequencyPerYear;

    @Column(name="priceperone")
    private double pricePerOne;

    @Column(name="descr")
    private String description;
}
