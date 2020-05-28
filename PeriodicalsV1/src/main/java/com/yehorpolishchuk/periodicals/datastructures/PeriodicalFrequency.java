package com.yehorpolishchuk.periodicals.datastructures;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Supplement class for form input handling of frequency indicator.
 *
 * Each constant can be used as frequency indicator, moreover it can be received from
 * fromDouble() method with double value as argument.
 *
 * Read more about frequency values can be used:
 * https://en.wikipedia.org/wiki/Periodical_literature
 * here in Frequency block
 * */
public enum PeriodicalFrequency {
    QUINQUENNIALLY (0.2),
    QUADRIENNIALLY (0.25),
    TRIENNIALLY (0.33),
    BIENNIALLY (0.5),
    ANNUALLY (1.0),
    SEMIANNUALLY (2.0),
    TRIANNUALLY (3.0),
    QUARTERLY (4.0),
    BIMONTHLY (6.0),
    SEMI_QUARTERLY (8.0),
    MONTHLY (12.0),
    SEMI_MONTHLY (24.0),
    BIWEEKLY (26.0),
    WEEKLY (52.0),
    SEMI_WEEKLY (104.0),

    /**
     * One per business day, can vary.
     * Frequency value is not strict.
     * */
    DAILY (300),

    ANOTHER(0);

    private final double freqDouble;

    PeriodicalFrequency(double frequency) {
        this.freqDouble = frequency;
    }

    public double getFrequencyDouble(){
        return this.freqDouble;
    }

    public static PeriodicalFrequency fromDouble(double frequency){
        for (PeriodicalFrequency freq : Arrays.asList(PeriodicalFrequency.values())) {
            if (Double.compare(freq.getFrequencyDouble(), frequency) == 0) {
                return freq;
            }
        }

        return PeriodicalFrequency.ANOTHER;
    }
}
