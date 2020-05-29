package com.yehorpolishchuk.periodicals.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class PeriodicalFrequencyTest {

    @Test
    public void getFrequencyDouble() {
        assertTrue(Double.compare(PeriodicalFrequency.QUINQUENNIALLY.getFrequencyDouble(), 0.2) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.QUADRIENNIALLY.getFrequencyDouble(), 0.25) ==0);
        assertTrue(Double.compare(PeriodicalFrequency.TRIENNIALLY.getFrequencyDouble(), 0.33) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.BIENNIALLY.getFrequencyDouble(), 0.5) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.ANNUALLY.getFrequencyDouble(), 1) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.SEMIANNUALLY.getFrequencyDouble(), 2) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.TRIANNUALLY.getFrequencyDouble(), 3) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.QUARTERLY.getFrequencyDouble(), 4) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.BIMONTHLY.getFrequencyDouble(), 6) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.SEMI_QUARTERLY.getFrequencyDouble(), 8) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.MONTHLY.getFrequencyDouble(), 12) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.SEMI_MONTHLY.getFrequencyDouble(), 24) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.BIWEEKLY.getFrequencyDouble(), 26) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.WEEKLY.getFrequencyDouble(), 52) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.SEMI_WEEKLY.getFrequencyDouble(), 104) == 0);
        assertTrue(Double.compare(PeriodicalFrequency.DAILY.getFrequencyDouble(), 300) == 0);

        assertTrue(Double.compare(PeriodicalFrequency.ANOTHER.getFrequencyDouble(), 0) == 0);
    }

    @Test
    public void fromDouble() {
        assertEquals(PeriodicalFrequency.QUINQUENNIALLY, PeriodicalFrequency.fromDouble(0.2));
        assertEquals(PeriodicalFrequency.QUADRIENNIALLY, PeriodicalFrequency.fromDouble(0.25));
        assertEquals(PeriodicalFrequency.TRIENNIALLY, PeriodicalFrequency.fromDouble(0.33));
        assertEquals(PeriodicalFrequency.BIENNIALLY, PeriodicalFrequency.fromDouble(0.5));
        assertEquals(PeriodicalFrequency.ANNUALLY, PeriodicalFrequency.fromDouble(1));
        assertEquals(PeriodicalFrequency.SEMIANNUALLY, PeriodicalFrequency.fromDouble(2));
        assertEquals(PeriodicalFrequency.TRIANNUALLY, PeriodicalFrequency.fromDouble(3));
        assertEquals(PeriodicalFrequency.QUARTERLY, PeriodicalFrequency.fromDouble(4));
        assertEquals(PeriodicalFrequency.BIMONTHLY, PeriodicalFrequency.fromDouble(6));
        assertEquals(PeriodicalFrequency.SEMI_QUARTERLY, PeriodicalFrequency.fromDouble(8));
        assertEquals(PeriodicalFrequency.MONTHLY, PeriodicalFrequency.fromDouble(12));
        assertEquals(PeriodicalFrequency.SEMI_MONTHLY, PeriodicalFrequency.fromDouble(24));
        assertEquals(PeriodicalFrequency.BIWEEKLY, PeriodicalFrequency.fromDouble(26));
        assertEquals(PeriodicalFrequency.WEEKLY, PeriodicalFrequency.fromDouble(52));
        assertEquals(PeriodicalFrequency.SEMI_WEEKLY, PeriodicalFrequency.fromDouble(104));
        assertEquals(PeriodicalFrequency.DAILY, PeriodicalFrequency.fromDouble(300));

        assertEquals(PeriodicalFrequency.ANOTHER, PeriodicalFrequency.fromDouble(0));

        assertEquals(PeriodicalFrequency.ANOTHER, PeriodicalFrequency.fromDouble(0.44));
        assertEquals(PeriodicalFrequency.ANOTHER, PeriodicalFrequency.fromDouble(0.0001));
        assertEquals(PeriodicalFrequency.ANOTHER, PeriodicalFrequency.fromDouble(-100));
    }
}