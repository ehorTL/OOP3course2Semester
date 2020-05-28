package com.yehorpolishchuk.periodicals.datastructures;

/**
 * State representing subscription state depending on the payment.
 * Two states needed: CONFIRMED and PENDING.
 * Other states for system expanding opportunity.
 * */
public enum State {
    CONFIRMED,
    PENDING,
    REJECTED,
    ERROR
}
