package com.yehorpolishchuk.PeriodicalsSpring.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Data
@Table(name="reader")
public class ReaderPrimitive {
    @Id
    @Column(name="rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="address")
    private int address;
}
