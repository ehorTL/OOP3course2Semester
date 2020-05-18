package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.datastructures.Periodical;
import com.yehorpolishchuk.periodicals.datastructures.PeriodicalFrequency;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PeriodicalDaoTest {

    private Periodical periodical = null;

    @Before
    public void setUp() throws Exception {
        periodical = new Periodical();
        periodical.setName("Science Journal");
        periodical.setFrequencyPerYear(PeriodicalFrequency.MONTHLY.getFrequencyDouble());
    }

    @Test
    public void addAndGetTest(){
//        int key = PeriodicalDao.addPeriodical(periodical);
    }
}