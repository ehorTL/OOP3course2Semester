package com.yehorpolishchuk.PeriodicalsSpring.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubscribeUtil {
    private Reader reader;
    private Payment payment;
    private Subscription subscription;
}

